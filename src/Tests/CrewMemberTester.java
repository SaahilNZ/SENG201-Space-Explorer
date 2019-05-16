package Tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SpaceExplorer.Crew;
import SpaceExplorer.FoodItem;
import SpaceExplorer.Item;
import SpaceExplorer.Outpost;
import SpaceExplorer.Planet;
import SpaceExplorer.Ship;
import SpaceExplorer.CrewMembers.CrewMember;
import SpaceExplorer.CrewMembers.Scout;

//Remaining tests to write: useItem, repairShip, searchPlanet, pilotShip, visitOutpost, purchaseItem
class CrewMemberTester {
	
	private Scout testCrew;
	private int startHealth;
	private int startHunger;
	private int startTired;
	
	@BeforeEach
	public void init() {
		testCrew = new Scout("Claptrap");
		startHealth = testCrew.getHealth();
		startHunger = testCrew.getHunger();
		startTired = testCrew.getTiredness();
	}
	
	@Test
	public void healthTest() {
		//Basic tests for functionality of damage and heal
		testCrew.modifyHealth(-60);
		testCrew.modifyHealth(10);
		assertEquals(startHealth - 60 + 10, testCrew.getHealth());
		
		//Tests health can't get higher than max
		testCrew.modifyHealth(800);
		assertEquals(startHealth, testCrew.getHealth());
		
	}

	
	@Test
	public void tiredTest() {
		//Tests if a crew member's tiredness can change
		testCrew.modifyTiredness(60);
		assertEquals(startTired+60, testCrew.getTiredness());
		
		//Tests if sleeping will reset a crew member's tiredness, and take one action
		testCrew.sleep();
		assertEquals(startTired, testCrew.getTiredness());
		assertEquals(1, testCrew.getActions());
		
		//Ensures crew members can't sleep after all actions are depleted 
		testCrew.sleep();
		testCrew.modifyTiredness(10);
		testCrew.sleep();
		assertEquals(startTired+10, testCrew.getTiredness());
		
		//Makes sure tiredness cannot fall below 0
		testCrew.modifyTiredness(200);
		assertEquals(testCrew.getMaxTiredness(), testCrew.getTiredness());
	}
	
	@Test
	public void hungerTest() {
		//Checks a crew member can become hungry
		testCrew.modifyHunger(60);
		assertEquals(startHunger+60, testCrew.getHunger());
		
		//Makes sure hunger can't fall below 0
		testCrew.modifyHunger(200);
		assertEquals(testCrew.getMaxHunger(), testCrew.getHunger());
	}
	
	@Test
	public void plagueTest() {
		//Checks plague status can be applied
		testCrew.setPlague(true);
		assertEquals(true, testCrew.hasPlague());
	}
	
	@Test
	public void repairTest() {
		Ship voyager = new Ship("Voyager", 200, 200);
		voyager.damageShield(100);
		voyager.damageShip(100);
		testCrew.repairShip(voyager);
		assertEquals(120, voyager.getShieldLevel());
		assertEquals(110, voyager.getHealth());
		
		testCrew.takeAction();
		testCrew.repairShip(voyager);
		assertEquals(120, voyager.getShieldLevel());
		assertEquals(110, voyager.getHealth());
	}
	
	
	@Test
	public void planetSearchTest() {
		//I don't know how to properly implement this test
		//Currently runs an ERROR
		Ship voyager = new Ship("Voyager", 200, 200);
		FoodItem burger = new FoodItem(1, "Burger", "Cooked Burger", 20, true, true, 20, 0);
		ArrayList<Item> testItems = new ArrayList<Item>();
		testItems.add(burger);
		ArrayList<CrewMember> testMembers = new ArrayList<CrewMember>();
		testMembers.add(testCrew);
		Crew canterbury = new Crew(testMembers, "Canterbury", voyager, 0, testItems);
		Outpost spaceOutpost = new Outpost("Space Station");
		Planet jupiter = new Planet("Jupiter", spaceOutpost);
		testCrew.searchPlanet(canterbury, jupiter);
		testCrew.searchPlanet(canterbury, jupiter);
		testCrew.setActions(2);
		testCrew.searchPlanet(canterbury, jupiter);
		testCrew.searchPlanet(canterbury, jupiter);
	}
	

}

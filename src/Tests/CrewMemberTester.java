package Tests;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SpaceExplorer.CrewMembers.Scout;

//Remaining tests to write: useItem, repairShip, searchPlanet, pilotShip, visitOutpost, purchaseItem
class CrewMemberTester {
	
	private Scout testCrew;
	
	@BeforeEach
	public void init() {
		testCrew = new Scout("Claptrap");
	}
	
	@Test
	public void healthTest() {
		int startHealth = testCrew.getHealth();
		
		//Basic tests for functionality of damage and heal
		testCrew.damageCrew(60);
		testCrew.heal(10);
		assertEquals(startHealth - 60 + 10, testCrew.getHealth());
		
		//Tests health can't get higher than max
		testCrew.heal(800);
		assertEquals(startHealth, testCrew.getHealth());
		
	}
	
	@Test
	public void deathTest() {
		//Makes sure members start alive
		assertEquals(true, testCrew.alive());
		
		//Tests if death is possible
		testCrew.damageCrew(200);
		assertEquals(false, testCrew.alive());
		
		//Makes sure members stay dead
		testCrew.heal(300);
		assertEquals(0, testCrew.getHealth());
		assertEquals(false, testCrew.alive());	
	}
	
	@Test
	public void tiredTest() {
		int startTired = testCrew.getTiredness();
		
		//Tests if a crew member's tiredness can change
		testCrew.becomeTired(60);
		assertEquals(startTired-60, testCrew.getTiredness());
		
		//Tests if sleeping will reset a crew member's tiredness, and take one action
		testCrew.sleep();
		assertEquals(startTired, testCrew.getTiredness());
		assertEquals(1, testCrew.getActions());
		
		//Ensures crew members can't sleep after all actions are depleted
		testCrew.sleep();
		testCrew.becomeTired(10);
		testCrew.sleep();
		assertEquals(startTired - 10, testCrew.getTiredness());
		
		//Makes sure tiredness cannot fall below 0
		testCrew.becomeTired(200);
		assertEquals(0, testCrew.getTiredness());
	}
	
	@Test
	public void hungerTest() {
		//Hunger tests mirror those in tiredTest()
		int startHunger = testCrew.getHunger();
		
		testCrew.becomeHungry(60);
		assertEquals(startHunger-60, testCrew.getHunger());
		
		testCrew.eat(60);
		assertEquals(startHunger, testCrew.getHunger());
		assertEquals(1, testCrew.getActions());
		
		testCrew.eat(10);
		testCrew.becomeHungry(10);
		testCrew.eat(10);
		assertEquals(startHunger - 10, testCrew.getHunger());
		
		testCrew.becomeHungry(200);
		assertEquals(0, testCrew.getHunger());
	}
	
	@Test
	public void plagueTest() {
		int startHealth = testCrew.getHealth();
		
		//Checks plague status can be changed, and that it damages the crew member afflicted
		testCrew.setPlague(true);
		assertEquals(true, testCrew.hasPlague());
		assertEquals(startHealth - 10, testCrew.getHealth());
		
		//Checks plague can be removed
		testCrew.setPlague(false);
		assertEquals(false, testCrew.hasPlague());
	}
	
	

}

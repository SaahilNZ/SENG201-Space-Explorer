package Tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SpaceExplorer.Ship;
import SpaceExplorer.CrewMembers.Scout;

/**
 * This class tests the functionality of the CrewMember class
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 *
 */
class CrewMemberTester {
	
	private Scout testCrew;
	private int startHealth;
	private int startHunger;
	private int startTired;
	
	/**
	 * Initializes a crew member object and records its starting values
	 */
	@BeforeEach
	public void init() {
		testCrew = new Scout("Claptrap");
		startHealth = testCrew.getHealth();
		startHunger = testCrew.getHunger();
		startTired = testCrew.getTiredness();
	}
	
	/**
	 * Tests the health variable of the Crew member class and the methods that manipulate it.
	 * Specifically checks damage/restore methods, as well as decreasing health by more than the max
	 */
	@Test
	public void healthTest() {
		//Basic tests for functionality of damage and heal
		testCrew.damageCrew(60);
		testCrew.restoreHealth(10);
		assertEquals(startHealth - 60 + 10, testCrew.getHealth());
		
		//Tests health can't get higher than max
		testCrew.restoreHealth(800);
		assertEquals(startHealth, testCrew.getHealth());
		
	}

	/**
	 * Tests crew members can have their tiredness increased. Additionally checks tiredness can be removed using sleep(),
	 * and that tiredness cannot fall beyond the specified limits
	 */
	@Test
	public void tiredTest() {
		//Tests if a crew member's tiredness can change
		testCrew.becomeTired(60);
		assertEquals(startTired+60, testCrew.getTiredness());
		
		//Tests if sleeping will reset a crew member's tiredness, and take one action
		testCrew.sleep();
		assertEquals(startTired, testCrew.getTiredness());
		assertEquals(1, testCrew.getActions());
		
		//Ensures crew members can't sleep after all actions are depleted 
		testCrew.sleep();
		testCrew.becomeTired(10);
		testCrew.sleep();
		assertEquals(startTired+10, testCrew.getTiredness());
		
		//Makes sure tiredness cannot fall below 0
		testCrew.becomeTired(200);
		assertEquals(testCrew.getMaxTiredness(), testCrew.getTiredness());
	}
	
	/**
	 * Tests hunger can be increased, and that it can't be increased beyond the specified maximum
	 */
	@Test
	public void hungerTest() {
		//Checks a crew member can become hungry
		testCrew.increaseHunger(60);
		assertEquals(startHunger+60, testCrew.getHunger());
		
		//Makes sure hunger can't fall below 0
		testCrew.increaseHunger(200);
		assertEquals(testCrew.getMaxHunger(), testCrew.getHunger());
	}
	
	/**
	 * Checks that crew members can have the plague effect applied to them
	 */
	@Test
	public void plagueTest() {
		//Checks plague status can be applied
		testCrew.setPlague(true);
		assertEquals(true, testCrew.hasPlague());
	}
	
	/**
	 * Checks that a crew member can repair a ship object
	 */
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

}

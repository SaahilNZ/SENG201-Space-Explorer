package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SpaceExplorer.CrewMembers.Doctor;
import SpaceExplorer.CrewMembers.Scout;

/**
 * This class tests the functionality of the Doctor sub class
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 *
 */
class DoctorTest {
	
	private Doctor testDoc;
	private Scout testCrew2;
	private int healthDoc;
	private int healthCrew2;
	
	/**
	 * Initializes a doctor object and another crew member for testing
	 */
	@BeforeEach
	public void init() {
		testDoc = new Doctor("Edward Scissor Hands");
		testCrew2 = new Scout("Claptrap");
		healthDoc = testDoc.getHealth();
		healthCrew2 = testCrew2.getHealth();
	}
	
	/**
	 * Test a doctor can heal itself when damaged, and that it has an action consumed
	 */
	@Test
	public void healSelf() {
		testDoc.modifyHealth(-20);
		testDoc.heal(testDoc);
		assertEquals(healthDoc, testDoc.getHealth());
		assertEquals(testDoc.getActions(), 1);
	}
	
	/**
	 * Tests a doctor can set its own plague status to false
	 */
	@Test
	public void cureSelf() {
		testDoc.setPlague(true);
		testDoc.curePlague(testDoc);
		assertEquals(0, testDoc.getActions());
		assertEquals(testDoc.hasPlague(), false);
	}
	
	/**
	 * Tests a doctor can heal another crew member
	 */
	@Test
	public void healOther() {
		testCrew2.modifyHealth(-20);
		testDoc.heal(testCrew2);
		assertEquals(healthCrew2, testCrew2.getHealth());
	}
	
	/**
	 * Tests a doctor can cure another crew member of the plague
	 */
	@Test
	public void cureOther() {
		testCrew2.setPlague(true);
		testDoc.curePlague(testCrew2);
		assertEquals(testCrew2.hasPlague(), false);
	}
	
	/**
	 * Tests doctor specific methods cannot be used with insufficient actions remaining
	 */
	@Test
	public void actionsTest() {
		testDoc.modifyHealth(-40);
		testDoc.setPlague(true);
		testDoc.takeAction();
		testDoc.takeAction();
		testDoc.heal(testDoc);
		testDoc.curePlague(testDoc);
		assertEquals(healthDoc - 40, testDoc.getHealth());
		assertEquals(true, testDoc.hasPlague());
	}

}

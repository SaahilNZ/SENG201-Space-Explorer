package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SpaceExplorer.CrewMembers.Doctor;
import SpaceExplorer.CrewMembers.Scout;

class DoctorTest {
	
	private Doctor testDoc;
	private Scout testCrew2;
	private int healthDoc;
	private int healthCrew2;
	
	@BeforeEach
	public void init() {
		testDoc = new Doctor("Edward Scissor Hands");
		testCrew2 = new Scout("Claptrap");
		healthDoc = testDoc.getHealth();
		healthCrew2 = testCrew2.getHealth();
	}
	
	@Test
	public void healSelf() {
		testDoc.damageCrew(20);
		testDoc.heal(testDoc);
		assertEquals(healthDoc, testDoc.getHealth());
		assertEquals(testDoc.getActions(), 1);
	}
	
	@Test
	public void cureSelf() {
		testDoc.setPlague(true);
		testDoc.curePlague(testDoc);
		assertEquals(0, testDoc.getActions());
		assertEquals(testDoc.hasPlague(), false);
	}
	
	@Test
	public void healOther() {
		testCrew2.damageCrew(20);
		testDoc.heal(testCrew2);
		assertEquals(healthCrew2, testCrew2.getHealth());
	}
	
	@Test
	public void cureOther() {
		testCrew2.setPlague(true);
		testDoc.curePlague(testCrew2);
		assertEquals(testCrew2.hasPlague(), false);
	}
	
	@Test
	public void actionsTest() {
		//Tests that heal and cure plague can't be used with no actions remaining
		testDoc.damageCrew(40);
		testDoc.setPlague(true);
		testDoc.takeAction();
		testDoc.takeAction();
		testDoc.heal(testDoc);
		testDoc.curePlague(testDoc);
		assertEquals(healthDoc - 40, testDoc.getHealth());
		assertEquals(true, testDoc.hasPlague());
	}

}

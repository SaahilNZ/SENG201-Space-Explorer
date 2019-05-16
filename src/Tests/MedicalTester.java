package Tests;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import SpaceExplorer.MedicalItem;
import SpaceExplorer.CrewMembers.Scout;

/**
 * This class tests the functionality of the MedicalItem class
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 *
 */
class MedicalTester {
	
	private Scout testCrew;
	private MedicalItem healthPod;
	
	/**
	 * Initializes a crew member and medical item for testing
	 */
	@BeforeEach
	public void init() {
		testCrew = new Scout("Claptrap");
		healthPod = new MedicalItem(1, "Health Pod", 20, true, true, 20, true);
	}
	
	/**
	 * Tests if a medical item can restore the correct amount of health
	 */
	@Test
	public void healthTest() {
		int startHealth = testCrew.getHealth();
		testCrew.modifyHealth(-30);
		testCrew.useItem(healthPod);
		assertEquals(startHealth-10, testCrew.getHealth());
	}
	
	/**
	 * Tests if a medical item can remove the plague status effect
	 */
	@Test
	public void plagueTest() {	
		testCrew.setPlague(true);
		testCrew.useItem(healthPod);
		assertEquals(false, testCrew.hasPlague());
	}
	
	/**
	 * Ensures an item can't be used after all actions consumed
	 */
	@Test
	public void useageTest() {
		int startHealth = testCrew.getHealth();
		testCrew.sleep();
		testCrew.sleep();
		testCrew.modifyHealth(-20);
		testCrew.setPlague(true);
		testCrew.useItem(healthPod);
		assertEquals(true, testCrew.hasPlague());
		assertEquals(startHealth-20, testCrew.getHealth());
	}
}

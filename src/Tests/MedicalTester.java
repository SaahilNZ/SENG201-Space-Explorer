package Tests;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import SpaceExplorer.MedicalItem;
import SpaceExplorer.CrewMembers.Scout;

class MedicalTester {
	
	private Scout testCrew;
	private MedicalItem healthPod;
	
	@BeforeEach
	public void init() {
		testCrew = new Scout("Claptrap");
		healthPod = new MedicalItem(1, "Health Pod", 20, true, true, 20, true);
	}
	
	@Test
	public void healthTest() {
		//Tests health is restored correctly
		int startHealth = testCrew.getHealth();
		testCrew.damageCrew(30);
		testCrew.useItem(healthPod);
		assertEquals(startHealth-10, testCrew.getHealth());
	}
	
	@Test
	public void plagueTest() {	
		//Tests plague can be cured
		testCrew.setPlague(true);
		testCrew.useItem(healthPod);
		assertEquals(false, testCrew.hasPlague());
	}
	
	
	@Test
	public void useageTest() {
		//Ensures an item can't be used after all actions consumed
		int startHealth = testCrew.getHealth();
		testCrew.sleep();
		testCrew.sleep();
		testCrew.damageCrew(20);
		testCrew.setPlague(true);
		testCrew.useItem(healthPod);
		assertEquals(true, testCrew.hasPlague());
		assertEquals(startHealth-20, testCrew.getHealth());
	}
}

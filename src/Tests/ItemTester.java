package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SpaceExplorer.FoodItem;
import SpaceExplorer.MedicalItem;
import SpaceExplorer.CrewMembers.Scout;

class ItemTester {
	
private Scout testCrew;
private MedicalItem healthPod;
private FoodItem burger;
	
	@BeforeEach
	public void init() {
		testCrew = new Scout("Claptrap");
		healthPod = new MedicalItem(1, "Health Pod", 20, true, true, 20, true);
		burger = new FoodItem(1, "Burger", "Cooked Burger", 20, true, true, 20);
	}
	
	
	@Test
	public void medicalTest() {
		//Tests health is restored correctly and plague can be removed
		int startHealth = testCrew.getHealth();
		testCrew.damageCrew(30);
		testCrew.getPlague();
		testCrew.useItem(healthPod);
		assertEquals(startHealth-10, testCrew.getHealth());
		assertEquals(false, testCrew.hasPlague());
		
		//Ensures an item can't be used after all actions consumed
		testCrew.sleep();
		testCrew.damageCrew(20);
		testCrew.getPlague();
		testCrew.useItem(healthPod);
		assertEquals(true, testCrew.hasPlague());
		assertEquals(startHealth-30, testCrew.getHealth());
		
	}
	
	@Test
	public void foodTest() {
		//Tests hunger is restored correctly
		int startHunger = testCrew.getHunger();
		testCrew.becomeHungry(50);
		testCrew.eat(burger);
		assertEquals(startHunger-30, testCrew.getHunger());
		
		//Checks food can't be eaten when no actions remain
		testCrew.eat(burger);
		testCrew.eat(burger);
		assertEquals(startHunger-10, testCrew.getHunger());
	}

}

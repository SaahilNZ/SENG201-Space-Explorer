package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SpaceExplorer.FoodItem;
import SpaceExplorer.CrewMembers.Scout;

class FoodTester {
	
	private Scout testCrew;
	private FoodItem burger;
	private FoodItem coffee;
	
	@BeforeEach
	public void init() {
		testCrew = new Scout("Claptrap");
		burger = new FoodItem(1, "Burger", "Cooked Burger", 20, true, true, 20, 0);
		coffee = new FoodItem(2, "Coffee", "Decaf", 20, true, true, 0, 50);
	}
	
	@Test
	public void hungerTest() {
		//Tests hunger is restored correctly
		int startHunger = testCrew.getHunger();
		testCrew.increaseHunger(50);
		testCrew.useItem(burger);
		assertEquals(startHunger+30, testCrew.getHunger());
	}
	
	@Test
	public void eatingTest() {
		//Checks food can't be eaten when no actions remain
		int startHunger = testCrew.getHunger();
		testCrew.increaseHunger(50);
		testCrew.sleep();
		testCrew.sleep();
		testCrew.useItem(burger);
		assertEquals(startHunger+50, testCrew.getHunger());
	}
	
	@Test
	public void tiredTest() {
		//Tests tiredness is restored correctly
		//#BUGGED
		int startTired = testCrew.getTiredness();
		testCrew.becomeTired(60);
		testCrew.useItem(coffee);
		assertEquals(startTired+10, testCrew.getTiredness());
	}

}

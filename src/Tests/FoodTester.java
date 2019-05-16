package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SpaceExplorer.FoodItem;
import SpaceExplorer.CrewMembers.Scout;

/**
 * This class tests the functionality of the FoodItem class
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 *
 */
class FoodTester {
	
	private Scout testCrew;
	private FoodItem burger;
	private FoodItem coffee;
	private int startHunger;
	private int startTired;
	
	/**
	 * Initializes a crew member and food items for testing
	 */
	@BeforeEach
	public void init() {
		testCrew = new Scout("Claptrap");
		burger = new FoodItem(1, "Burger", "Cooked Burger", 20, true, true, 20, 0);
		coffee = new FoodItem(2, "Coffee", "Decaf", 20, true, true, 0, 50);
		startHunger = testCrew.getHunger();
		startTired = testCrew.getTiredness();
		
	}
	
	/**
	 * Tests hunger is restored correctly
	 */
	@Test
	public void hungerTest() {
		testCrew.increaseHunger(50);
		testCrew.useItem(burger);
		assertEquals(startHunger+30, testCrew.getHunger());
	}
	
	/**
	 * Checks food can't be eaten when no actions remain
	 */
	@Test
	public void eatingTest() {
		testCrew.increaseHunger(50);
		testCrew.sleep();
		testCrew.sleep();
		testCrew.useItem(burger);
		assertEquals(startHunger+50, testCrew.getHunger());
	}
	
	/**
	 * Tests tiredness is restored correctly
	 */
	@Test
	public void tiredTest() {
		testCrew.becomeTired(60);
		testCrew.useItem(coffee);
		assertEquals(startTired+30, testCrew.getTiredness());
	}
	
	/**
	 * Tests a food item can be cooked
	 */
	@Test
	public void cookTest() {
		burger.cookFood();
		assertEquals(burger.getName(), "Cooked Burger");
		assertEquals(burger.getHungerAmount(), 40);
		//Makes sure it can't be cooked twice
		burger.cookFood();
		assertEquals(burger.getName(), "Cooked Burger");
		assertEquals(burger.getHungerAmount(), 40);
	}

}

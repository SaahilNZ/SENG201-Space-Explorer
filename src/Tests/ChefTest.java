package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SpaceExplorer.FoodItem;
import SpaceExplorer.CrewMembers.Chef;
/**
 * Class to test the Chef subclass
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 *
 */
class ChefTest {
	
	private Chef testChef;
	private FoodItem burger;
	private FoodItem sammy;
	
	/**
	 * Initializes a chef object and some food items for testing
	 */
	@BeforeEach
	public void init() {
		testChef = new Chef("Claptrap");
		burger = new FoodItem(1, "Burger", "Cooked Burger", 20, true, true, 20, 0);
		sammy = new FoodItem(1, "Sammy", "Toasted Sammy", 20, true, true, 20, 0);
	}
	
	/**
	 * Tests a chef can use their cook method on a food item object. Additionally checks that the object cannot be cooked
	 * twice, and that a chef cannot cook and object when it is out of actions
	 */
	@Test
	public void cookingTest() {
		testChef.cook(burger);
		assertEquals(burger.getName(), "Cooked Burger");
		assertEquals(burger.getHungerAmount(), 40);
		
		testChef.cook(burger);
		assertEquals(burger.getName(), "Cooked Burger");
		assertEquals(burger.getHungerAmount(), 40);
		
		testChef.cook(sammy);
		assertEquals(sammy.getName(), "Sammy");
		assertEquals(sammy.getHungerAmount(), 20);
		assertEquals(0, testChef.getActions());
	}

}

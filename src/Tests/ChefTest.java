package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SpaceExplorer.FoodItem;
import SpaceExplorer.CrewMembers.Chef;

class ChefTest {
	
	private Chef testChef;
	private FoodItem burger;
	private FoodItem sammy;
	
	@BeforeEach
	public void init() {
		testChef = new Chef("Claptrap");
		burger = new FoodItem(1, "Burger", "Cooked Burger", 20, true, true, 20, 0);
		sammy = new FoodItem(1, "Sammy", "Toasted Sammy", 20, true, true, 20, 0);
	}
	
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

package SpaceExplorer.CrewMembers;

import SpaceExplorer.FoodItem;

public class Chef extends CrewMember{
	private int hunger = 120;
	private int maxHunger = 120;
	
	public Chef(String name) {
		super(name);
	}
	
	public void cook(FoodItem food) {
		food.cookFood();
	}
	
}

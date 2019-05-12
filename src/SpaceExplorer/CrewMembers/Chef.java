package SpaceExplorer.CrewMembers;

import SpaceExplorer.FoodItem;

public class Chef extends CrewMember {
	public Chef(String name) {
		super(name, "Chef", DEFAULT_HEALTH, 120, DEFAULT_TIREDNESS);
	}
	
	public void cook(FoodItem food) {
		if (getActions() > 0) {			
			food.cookFood();
			takeAction();
		}
	}
	
}

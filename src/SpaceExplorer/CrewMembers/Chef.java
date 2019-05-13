package SpaceExplorer.CrewMembers;

import SpaceExplorer.FoodItem;

public class Chef extends CrewMember {
	public Chef(String name) {
		super(name, "Chef", DEFAULT_HEALTH, 120, DEFAULT_TIREDNESS);
	}
	
	public String cook(FoodItem food) {
		String message = "";
		if (takeAction()) {
			message += getName() + " has successfully cooked the " + food.getName() + ".";
			food.cookFood();
		} else {
			
		}
		return message;
	}
	
}

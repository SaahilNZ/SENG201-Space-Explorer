package SpaceExplorer.CrewMembers;

import SpaceExplorer.FoodItem;

public class Chef extends CrewMember {
	public Chef(String name) {
		super(name, "Chef", DEFAULT_HEALTH, 120, DEFAULT_TIREDNESS);
	}
	
	public ActionResult cook(FoodItem food) {
		String message = "";
		boolean success = false;
		if (takeAction()) {
			message += getName() + " has successfully cooked the " + food.getName() + ".";
			food.cookFood();
			success = true;
		} else {
			message += getName() + " does not have enough actions left to cook food.";
		}
		return new ActionResult(message, success);
	}
	
}

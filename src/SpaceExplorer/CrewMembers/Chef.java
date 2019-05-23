package SpaceExplorer.CrewMembers;

import SpaceExplorer.FoodItem;

/**
 * This class implements a Chef class. Extends the crew member class with an additional method that allows
 * for a Food Item to be cooked.
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 *
 */
public class Chef extends CrewMember {
	
	/**
	 * Default crew member class constructor
	 * 
	 * @param name			Name of the chef
	 */
	public Chef(String name) {
		super(name, "Chef", DEFAULT_HEALTH, 120, DEFAULT_TIREDNESS);
	}
	
	/**
	 * Allows the chef to use the cookFood method of a FoodItem object
	 * 
	 * @param food			The item of food to be cooked
	 * @return				A string based on whether the action was successful or not
	 */
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

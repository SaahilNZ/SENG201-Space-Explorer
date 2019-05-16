package SpaceExplorer.CrewMembers;

import SpaceExplorer.Ship;

/**
 * This class implements a Mechanic and its associated functionality
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 *
 */
public class Mechanic extends CrewMember {
	
	/**
	 * Uses default constructor method of the parent class
	 * 
	 * @param name			Name of the mechanic
	 */
	public Mechanic(String name) {
		super(name, "Mechanic", DEFAULT_HEALTH, DEFAULT_HUNGER, DEFAULT_TIREDNESS);
	}
	
	/**
	 * Takes a ship and allows a mechanic to restore its shields and health if they have enough actions
	 * 
	 * @param ship			The crew's ship
	 * @return 				A message based on whether the actions was successful or not
	 */
	@Override
	public ActionResult repairShip(Ship ship) {
		String message = "";
		boolean success = false;
		if (takeAction()) {
			ship.repairShield(60);
			ship.repairShip(40);
			message += getName() + " has repaired the " + ship.toString() + ".\n";
			message += "The " + ship.toString() + "'s shields have been restored by 60 points.\n";
			message += "The " + ship.toString() + "'s health has been restored by 40 points.\n";
			success = true;
		} else {
			message += getName() + " does not have enough actions left to repair the ship.";
		}
		return new ActionResult(message, success);
	}
	
}

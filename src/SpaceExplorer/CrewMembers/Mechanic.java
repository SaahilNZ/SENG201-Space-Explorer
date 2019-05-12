package SpaceExplorer.CrewMembers;

import SpaceExplorer.Ship;

public class Mechanic extends CrewMember {
	public Mechanic(String name) {
		super(name, "Mechanic", DEFAULT_HEALTH, DEFAULT_HUNGER, DEFAULT_TIREDNESS);
	}
	
	@Override
	public String repairShip(Ship ship) {
		String message = "";
		if (getActions() > 0) {
			ship.repairShield(60);
			ship.repairShip(40);
			message += getName() + " has repaired the " + ship.toString() + ".\n";
			message += "The " + ship.toString() + "'s shields have been restored by 60 points.\n";
			message += "The " + ship.toString() + "'s health has been restored by 40 points.\n";
			takeAction();
		} else {
			message += getName() + " does not have enough actions left to repair the ship.";
		}
		return message;
	}
	
}

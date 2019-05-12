package SpaceExplorer.CrewMembers;

import SpaceExplorer.Ship;

public class Mechanic extends CrewMember {
	public Mechanic(String name) {
		super(name, "Mechanic", DEFAULT_HEALTH, DEFAULT_HUNGER, DEFAULT_TIREDNESS);
	}
	
	@Override
	public void repairShip(Ship ship) {
		if (super.getActions()>0) {
			ship.repairShield(60);
			ship.repairShip(40);
			super.takeAction();
		}
	}
	
}

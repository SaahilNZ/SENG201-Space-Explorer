package SpaceExplorer.CrewMembers;

public class Mechanic extends CrewMember {
	public Mechanic(String name) {
		super(name, "Mechanic", DEFAULT_HEALTH, DEFAULT_HUNGER, DEFAULT_TIREDNESS);
	}
	
	@Override
	public void repairShip() {
		//Repair the ship
	}
}

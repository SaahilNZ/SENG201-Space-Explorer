package SpaceExplorer;

public class RandomEventController {
	private static final int DAMAGE_AMOUNT = 10;
	
	public RandomEventController() { }
	
	public void stealItem(Crew crew) {
		int index = (int)(Math.random() * crew.getItems().size());
		Item item = crew.getItems().remove(index);
		System.out.println("Your " + item.getName() + " was stolen by space pirates!");
	}
	
	public void infectCrew(Crew crew) {
		int index = (int)(Math.random() * crew.getCrewMembers().size());
		CrewMember crewMember = crew.getCrewMembers().get(index);
		crewMember.setPlague(true);
		System.out.println(crewMember.getName() + " has contracted the space plague.");
	}
	
	public void damageShip(Ship ship) {
		ship.damageShip(DAMAGE_AMOUNT);
		System.out.println("The Starship " + ship.getName()
			+ " has taken " + DAMAGE_AMOUNT + " points of damage.");
	}
}

package SpaceExplorer.CrewMembers;

public class Doctor extends CrewMember {
	public Doctor(String name) {
		super(name, "Doctor", 80, DEFAULT_HUNGER, DEFAULT_TIREDNESS);
    }

	public String curePlague(CrewMember member) {
		String message = "";
		if (getActions() >= 2) {
			message += getName() + " has successfully cured " + member.getName() + " of the space plague.";
			member.setPlague(false);
			takeAction();
			takeAction();
		} else {
			message += getName() + " does not have enough actions to cure someone of space plague.";
		}
		return message;
	}
	
	public String heal(CrewMember member) {
		String message = "";
		if (takeAction()) {
			message += getName() + " has successfully healed " + member.getName() + " for 20 health points.";
			member.restoreHealth(20);
		} else {
			message += getName() + " does not have enough actions to heal someone";
		}
		return message;
	}
	
}

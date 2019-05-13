package SpaceExplorer.CrewMembers;

public class Doctor extends CrewMember {
	public Doctor(String name) {
		super(name, "Doctor", 80, DEFAULT_HUNGER, DEFAULT_TIREDNESS);
    }

	public void curePlague(CrewMember member) {
		if (getActions() >= 2) {			
			member.setPlague(false);
			takeAction();
			takeAction();
		}
	}
	
	public void heal(CrewMember member) {
		if (getActions() > 0) {
			member.restoreHealth(20);
			takeAction();
		}
	}
	
}

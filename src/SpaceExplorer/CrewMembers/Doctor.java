package SpaceExplorer.CrewMembers;

public class Doctor extends CrewMember {
	public Doctor(String name) {
		super(name, "Doctor", 80, DEFAULT_HUNGER, DEFAULT_TIREDNESS);
    }

	public void curePlague(CrewMember member) {
		member.setPlague(false);
	}
	
	public void heal(CrewMember member) {
		member.heal(20);
	}
	
}

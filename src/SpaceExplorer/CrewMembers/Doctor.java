package SpaceExplorer.CrewMembers;

public class Doctor extends CrewMember{
	private int health = 80;
	private int maxHealth = 80;

	public Doctor(String name) {
	    super(name);
    }

	public void curePlague(CrewMember member) {
		member.setPlague(false);
	}
	
	public void heal(CrewMember member) {
		member.heal(20);
	}
	
}

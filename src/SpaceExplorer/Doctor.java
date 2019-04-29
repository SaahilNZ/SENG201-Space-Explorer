package SpaceExplorer;

public class Doctor {
	private int health = 80;
	private int maxHealth = 80;
	
	public void curePlague(CrewMember member) {
		member.setPlague(false);
	}
	
	public void heal(CrewMember member) {
		member.heal(20);
	}
	
}

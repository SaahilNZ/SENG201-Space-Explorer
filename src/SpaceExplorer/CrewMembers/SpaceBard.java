package SpaceExplorer.CrewMembers;

public class SpaceBard extends CrewMember{
	private int health = 90;
	private int maxHealth = 90;
	private int tiredness = 90;
	private int maxTiredness = 90;

	public SpaceBard(String name) {
		super(name);
	}
	
	public void performMusic() {
		//Reduces tiredness for all crew members
	}
	
}

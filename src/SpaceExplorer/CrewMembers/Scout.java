package SpaceExplorer.CrewMembers;

public class Scout extends CrewMember {
	private int health = 110;
	private int maxHealth = 110;
	private int tiredness = 120;
	private int maxTiredness = 120;

	public Scout(String name) {
		super(name);
	}
	
	@Override
	public void searchPlanet() {
		//Search a planet. The scout has more items returned for a planet search than any other class.
	}
}

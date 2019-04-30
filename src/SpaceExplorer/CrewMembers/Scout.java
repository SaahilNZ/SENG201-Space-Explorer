package SpaceExplorer.CrewMembers;

public class Scout extends CrewMember {
	public Scout(String name) {
		super(name, "Scout", 110, DEFAULT_HUNGER, 120);
	}
	
	@Override
	public void searchPlanet() {
		//Search a planet. The scout has more items returned for a planet search than any other class.
	}
}

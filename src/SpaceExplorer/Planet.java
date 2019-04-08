package SpaceExplorer;

public class Planet {
	private final String name;
	private boolean shipPartFound;
	
	public Planet(String name) {
		this.name = name;
	}
	
	public boolean hasShipPart() {
		return !shipPartFound;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}

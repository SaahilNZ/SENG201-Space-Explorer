package SpaceExplorer;

public class Planet {
	private final String name;
	private boolean shipPartFound;
	private Outpost outpost;
	
	public Planet(String name, Outpost outpost) {
		this.name = name;
		this.outpost = outpost;
	}
	
	public boolean hasShipPart() {
		return !shipPartFound;
	}
	
	public String getName() {
		return name;
	}
	
	public Outpost getOutpost() {
		return outpost;
	}
	
	@Override
	public String toString() {
		String output = "Planet " + name + "\n";
		output += "This planet " + (shipPartFound ? "does not have a ship part.\n" : "has a ship part.\n");
		output += (outpost == null ? "There is no outpost on this planet.\n"
				: "There is an outpost named " + outpost.getName() + " on this planet.\n");
		return output;
	}
}

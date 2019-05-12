package SpaceExplorer;

public class Planet {
	private final String name;
	private boolean hasShipPart;
	private Outpost outpost;
	
	public Planet(String name, Outpost outpost) {
		this.name = name;
		this.hasShipPart = true;
		this.outpost = outpost;
	}
	
	public boolean hasShipPart() {
		return hasShipPart;
	}
	
	public String getName() {
		return name;
	}
	
	public Outpost getOutpost() {
		return outpost;
	}
	
	public String getDescription() {
		String output = (outpost == null ? "There is no outpost on this planet.\n"
				: "There is an outpost named " + outpost.getName() + " on this planet.\n");
		output += "\n";
		output += "Scans indicate that there " + (hasShipPart ? "is a ship part located on this planet.\n"
				: "are no ship parts on this planet.");
		return output;
	}
	
	public void findShipPart() {
		hasShipPart = false;
	}
	
	@Override
	public String toString() {
		return "Planet " + name;
	}
}

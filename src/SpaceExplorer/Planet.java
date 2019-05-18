package SpaceExplorer;

/**
 * This class implements a planet object. Each planet contains one ship part and one outpost
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 *
 */
public class Planet {
	private final String name;
	private boolean hasShipPart;
	private Outpost outpost;
	
	/**
	 * Constructor for the planet class. 
	 * 
	 * @param name			Name of the planet
	 * @param outpost		Name of the outpost connect with the planet
	 */
	public Planet(String name, Outpost outpost) {
		this.name = name;
		this.hasShipPart = true;
		this.outpost = outpost;
	}
	
	/**
	 * Whether the planet still has a ship part
	 * @return				True if the planet has a ship part
	 */
	public boolean hasShipPart() {
		return hasShipPart;
	}
	
	/**
	 * Getter method for the name of the planet
	 * @return				The name of the planet
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter method for the outpost connected with the planet
	 * @return				The outpost for this planet
	 */
	public Outpost getOutpost() {
		return outpost;
	}
	
	/**
	 * A string description of the planet for the player to read. Tells the player the name of the
	 * associated outpost, planet name and whether there is a ship part there
	 * @return 				The string description of the planet
	 */
	public String getDescription() {
		String output = (outpost == null ? "There is no outpost on this planet.\n"
				: "There is an outpost named " + outpost.getName() + " on this planet.\n");
		output += "\n";
		output += "Scans indicate that there " + (hasShipPart ? "is a ship part located on this planet.\n"
				: "are no ship parts on this planet.");
		return output;
	}
	
	/**
	 * Removes the ship part from the planet
	 */
	public void findShipPart() {
		hasShipPart = false;
	}
	
	@Override
	public String toString() {
		return "Planet " + name;
	}
}

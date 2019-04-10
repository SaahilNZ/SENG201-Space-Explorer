package SpaceExplorer;

public class Outpost {
	private final String name;
	
	public Outpost(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void refreshInventory() {
		// refresh the inventory
	}
	
	@Override
	public String toString() {
		String output = "Outpost name: " + name + "\n";
		output += "Inventory:\n";
		// append items
		return output;
	}
}

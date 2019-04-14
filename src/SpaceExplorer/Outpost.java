package SpaceExplorer;

import java.util.ArrayList;

public class Outpost {
	private final String name;
	private ArrayList<Item> inventory;
	
	public Outpost(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void refreshInventory() {
		// refresh the inventory
	}
	
	public void viewItems() {
		for (Item item : inventory) {
			item.viewObject();
		}
	}
	
	@Override
	public String toString() {
		String output = "Outpost name: " + name + "\n";
		output += "Inventory:\n";
		// append items
		return output;
	}
}

package SpaceExplorer;

import java.util.ArrayList;

public class Outpost {
	private final String name;
	private ArrayList<Item> inventory;
	
	public Outpost(String name) {
		this.name = name;
		this.inventory = new ArrayList<Item>();
	}
	
	public String getName() {
		return name;
	}
	
	public void refreshInventory() {
		// refresh the inventory
	}
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	public boolean removeItem(Item item) {
		return inventory.remove(item);
	}
	
	@Override
	public String toString() {
		return name;
	}
}

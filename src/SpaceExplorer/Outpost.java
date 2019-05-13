package SpaceExplorer;

import java.util.ArrayList;
import java.util.Random;

public class Outpost {
	private final String name;
	private ArrayList<Item> inventory;
	
	public Outpost(String name) {
		this.name = name;
		this.inventory = new ArrayList<Item>();
		refreshInventory();
	}
	
	public String getName() {
		return name;
	}
	
	public void refreshInventory() {
		this.inventory = new ArrayList<Item>();
		ArrayList<Item> purchasableItems = new ArrayList<Item>();
		for (Item item : Game.getCurrentGame().getItems()) {
			if (item.canBeSold()) {
				purchasableItems.add(item);
			}
		}
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			inventory.add(purchasableItems.get(random.nextInt(purchasableItems.size())));
		}
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

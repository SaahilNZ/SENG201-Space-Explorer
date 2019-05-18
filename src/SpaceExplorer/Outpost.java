package SpaceExplorer;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class implements an outpost capable of selling items to the player
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 *
 */
public class Outpost {
	private final String name;
	private ArrayList<Item> inventory;
	
	/**
	 * Constructor for an outpost
	 * 
	 * @param name			Name of the outpost
	 */
	public Outpost(String name) {
		this.name = name;
		this.inventory = new ArrayList<Item>();
		refreshInventory();
	}
	
	/**
	 * Getter method for the outpost's name
	 * 
	 * @return			The outpost's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Refreshes the current inventory of the outpost. Picks the new inventory out of the available
	 * item in the game that satisfy the canBeSold criteria. Items are chosen randomly
	 */
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
	
	/**
	 * Getter method for the current outpost inventory
	 * 
	 * @return				An ArrayList of the outpost's inventory
	 */
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	/**
	 * Removes an item from the outpost's current inventory
	 * 
	 * @param item			The item to be removed
	 * @return				Whether the item was part of the inventory
	 */
	public boolean removeItem(Item item) {
		return inventory.remove(item);
	}
	
	@Override
	public String toString() {
		return name;
	}
}

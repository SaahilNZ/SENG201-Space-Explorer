package SpaceExplorer;

/**
 * This class implements a food item that can be cooked and used to reduce the hunger level of a crew member
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 *
 */
public class FoodItem extends Item {
	private int hungerAmount;
	private boolean isCooked = false;
	private String cookedName;
	private int tiredAmount;
	
	/**
	 * Constructor for a food item
	 * 
	 * @param id				The item's id
	 * @param rawName			The name of the item when it is uncooked
	 * @param cookedName		The name of the item once it has been cooked
	 * @param price				The price of the item
	 * @param canBeFound		Whether the item can be found
	 * @param canBeSold			Whether the item can be sold at an outpost
	 * @param hungerAmount		The amount of hunger the item can restore
	 * @param tiredAmount		The amount of tiredness the item can restore
	 */
	public FoodItem(int id, String rawName, String cookedName, int price, boolean canBeFound,
			boolean canBeSold, int hungerAmount, int tiredAmount) {
		this(id, rawName, cookedName, price, canBeFound, canBeSold, hungerAmount, tiredAmount, false);
	}
	
	public FoodItem(int id, String rawName, String cookedName, int price, boolean canBeFound,
			boolean canBeSold, int hungerAmount, int tiredAmount, boolean isCooked) {
		this.id = id;
		this.name = rawName;
		this.cookedName = cookedName;
		this.price = price;
		this.canBeFound = canBeFound;
		this.canBeSold = canBeSold;
		this.hungerAmount = hungerAmount;
		this.tiredAmount = tiredAmount;
	}
	
	/**
	 * Getter method for the amount of hunger the item restores
	 * 
	 * @return					The amount of hunger restored
	 */
	public int getHungerAmount() {
		return hungerAmount;
	}
	
	/**
	 * Getter method for the amount of tiredness the item restores
	 * @return					The amoun to tiredness restored
	 */
	public int getTiredAmount() {
		return tiredAmount;
	}
	
	/**
	 * Method to cook a food item. Increases the hunger restored by 20 and changes the item to its cooked name.
	 * Only cooks an item if it hasn't already been cooked
	 */
	public void cookFood() {
		if (isCooked != true) {
			hungerAmount += 20;
			isCooked = true;
			name = cookedName;
		}
	}
	
	/**
	 * Getter method to check if the object has been cooked
	 * 
	 * @return					True if cooked
	 */
	public boolean isCooked() {
		return isCooked;
	}
	
	/**
	 * Creates a copy of a food item
	 */
	@Override
	public Item createCopy() {
		Item copy = new FoodItem(id, "" + name, "" + cookedName, price, canBeFound,
			canBeSold, hungerAmount, tiredAmount, isCooked);
		return copy;
	}
}

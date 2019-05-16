package SpaceExplorer;

/**
 * This class implements a basic item. Used as an abstract for FoodItem and MedicalItem classes
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 *
 */
public abstract class Item implements Cloneable {
	protected int id;
	protected int price;
	protected boolean canBeFound;
	protected boolean canBeSold;
	protected String name;
	
	/**
	 * Getter for the item's id
	 * 
	 * @return			The items id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Getter for the item's price
	 * 
	 * @return			The items price
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * Getter for the item's name
	 * 
	 * @return			The item's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter for if the item can be found on a planet
	 * 
	 * @return			True if the item can be found
	 */
	public boolean canBeFound() {
		return canBeFound;
	}
	
	/**
	 * Getter for if the item can be bought at an outpost
	 * 
	 * @return			True if the item can be sold
	 */
	public boolean canBeSold() {
		return canBeSold;
	}	
	
	@Override
	public String toString() {
		return name;
	}
	
	public abstract Item createCopy();
}

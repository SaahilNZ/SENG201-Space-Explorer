package SpaceExplorer;

/**
 * This class is used when creating an item and associates a unique item id to that object
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 *
 */
public class ItemFactory {
	private static int itemId = 0;
	
	/**
	 * Associates a unique id with a medical item
	 * 
	 * @param name				Name of the item
	 * @param price				Price of the item
	 * @param canBeFound		Whether the item can be found on a planet
	 * @param canBeSold			Whether the item can be sold at an outpost
	 * @param restoreAmount		How much health the item can restore
	 * @param curesPlague		Whether the item cures plague
	 * @return					A medical item object
	 */
	public static MedicalItem createMedicalItem(String name, int price,
			boolean canBeFound, boolean canBeSold, int restoreAmount, boolean curesPlague) {
		int id = itemId;
		itemId++;
		return new MedicalItem(id, name, price, canBeFound, canBeSold, restoreAmount, curesPlague);
	}
	
	/**
	 * Associates a unique id with a food item
	 * 
	 * @param name				Name of the item
	 * @param cookedName		The cooked name of the item
	 * @param price				The price of the item
	 * @param canBeFound		Whether the item can be found on a planet
	 * @param canBeSold			Whether the item can be sold at an outpost
	 * @param hungerAmount		How much hunger can be reduced by the item
	 * @param tiredAmount		How much tiredness can be reduced by the item
	 * @return					A food item object
	 */
	public static FoodItem createFoodItem(String name, String cookedName, int price,
			boolean canBeFound, boolean canBeSold, int hungerAmount, int tiredAmount) {
		int id = itemId;
		itemId++;
		return new FoodItem(id, name, cookedName, price, canBeFound, canBeSold, hungerAmount, tiredAmount);
	}
}

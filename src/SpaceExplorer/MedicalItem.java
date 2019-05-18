package SpaceExplorer;
/**
 * This class implements a medical item. Can be used to restore a crew member's health or remove the plague
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 *
 */
public class MedicalItem extends Item {
	private int restoreAmount;
	private boolean curesPlague;
	
	/**
	 * Constructor for a Medical item
	 * 
	 * @param id				Id of the item
	 * @param name				Name of the item
	 * @param price				Price of the item
	 * @param canBeFound		Whether the item can be found
	 * @param canBeSold			Whether the item can be sold
	 * @param restoreAmount		The amount of health the item can restore
	 * @param curesPlague		Whether the item cures plague
	 */
	public MedicalItem(int id, String name, int price, boolean canBeFound,
			boolean canBeSold, int restoreAmount, boolean curesPlague) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.canBeFound = canBeFound;
		this.canBeSold = canBeSold;
		this.restoreAmount = restoreAmount;
		this.curesPlague = curesPlague;
	}
	
	/**
	 * Getter method for how much health the item can restore to a crew member
	 * 
	 * @return			The amount of health that can be restored
	 */
	public int getRestoreAmount() {
		return restoreAmount;
	}
	
	/**
	 * Getter method to see if the item cures the plague
	 * 
	 * @return			True if the item cures the plague
	 */
	public boolean curesPlague() {
		return curesPlague;
	}
	
	/**
	 * Creates a copy of the medical item
	 */
	@Override
	public Item createCopy() {
		Item item = new MedicalItem(id, "" + name, price, canBeFound, canBeSold, restoreAmount, curesPlague);
		return item;
	}
}

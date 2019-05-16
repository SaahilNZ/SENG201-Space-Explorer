package SpaceExplorer;

public class FoodItem extends Item {
	private int hungerAmount;
	private boolean isCooked = false;
	private String cookedName;
	private int tiredAmount;
	

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
	
	public int getHungerAmount() {
		return hungerAmount;
	}
	
	public int getTiredAmount() {
		return tiredAmount;
	}
	
	public void cookFood() {
		if (isCooked != true) {
			hungerAmount += 20;
			isCooked = true;
			name = cookedName;
		}
	}
	
	public boolean isCooked() {
		return isCooked;
	}

	@Override
	public Item createCopy() {
		Item copy = new FoodItem(id, "" + name, "" + cookedName, price, canBeFound,
			canBeSold, hungerAmount, tiredAmount, isCooked);
		return copy;
	}
}

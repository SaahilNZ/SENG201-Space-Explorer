package SpaceExplorer;

public class FoodItem extends Item {
	private int hungerAmount;
	private boolean isCooked = false;
	private String cookedName;
	private int tiredAmount;
	
	public FoodItem(int id, String name, String cookedName, int price, boolean canBeFound,
			boolean canBeSold, int hungerAmount, int tiredAmount) {
		this.id = id;
		this.name = name;
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
		}
	}

	@Override
	public void viewObject() {
		System.out.println("Name: " + (isCooked ? cookedName : name));
		System.out.println("Price: " + price);
		System.out.println("Restores " + hungerAmount + " point(s) of hunger");
		System.out.println();
	}

}

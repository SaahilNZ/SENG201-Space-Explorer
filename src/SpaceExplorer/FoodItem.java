package SpaceExplorer;

public class FoodItem extends Item {
	private int hungerAmount;
	private boolean isCooked = false;
	private String cookedName;
	
	public FoodItem(int id, String name, String cookedName, int price, boolean canBeFound,
			boolean canBeSold, int hungerAmount) {
		this.id = id;
		this.name = name;
		this.cookedName = cookedName;
		this.price = price;
		this.canBeFound = canBeFound;
		this.canBeSold = canBeSold;
		this.hungerAmount = hungerAmount;
	}
	
	public int getHungerAmount() {
		return hungerAmount;
	}
	
	public void cookFood() {
		if (isCooked != true) {
			hungerAmount += 20;
			isCooked = true;
		}else {
			System.out.println("This has already been cooked.");
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

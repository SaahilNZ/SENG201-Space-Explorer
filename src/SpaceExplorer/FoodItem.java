package SpaceExplorer;

public class FoodItem extends Item {
	private int hungerAmount;
	
	public FoodItem(int id, String name, int price, boolean canBeFound,
			boolean canBeSold, int hungerAmount) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.canBeFound = canBeFound;
		this.canBeSold = canBeSold;
		this.hungerAmount = hungerAmount;
	}
	
	public int getHungerAmount() {
		return hungerAmount;
	}

	@Override
	public void viewObject() {
		System.out.println("Name: " + name);
		System.out.println("Price: " + price);
		System.out.println("Restores " + hungerAmount + " point(s) of hunger");
		System.out.println();
	}

}

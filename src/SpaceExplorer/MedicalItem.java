package SpaceExplorer;

public class MedicalItem extends Item {
	private int restoreAmount;
	private boolean curesPlague;
	
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
	
	public int getRestoreAmount() {
		return restoreAmount;
	}
	
	public boolean curesPlague() {
		return curesPlague;
	}

	@Override
	public Item createCopy() {
		Item item = new MedicalItem(id, "" + name, price, canBeFound, canBeSold, restoreAmount, curesPlague);
		return item;
	}
}

package SpaceExplorer;

public abstract class Item {
	protected int id;
	protected int price;
	protected boolean canBeFound;
	protected String name;
	
	public int getId() {
		return id;
	}
	
	public int getPrice() {
		return price;
	}
	
	public boolean canBeFound() {
		return canBeFound;
	}
	
	public abstract void viewObject();
	
	@Override
	public String toString() {
		return name;
	}
}

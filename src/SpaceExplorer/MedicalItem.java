package SpaceExplorer;

public class MedicalItem extends Item {
	private int restoreAmount;
	private boolean curesPlague;
	
	public MedicalItem(int restoreAmount, boolean curesPlague) {
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
	public void viewObject() {
		System.out.println("Name: " + name);
		System.out.println("Price: " + price);
		System.out.println("Restores " + restoreAmount + " lost health point(s)");
		if (curesPlague) {
			System.out.println("This item cures Space Plague");
		}
		System.out.println();
	}
}

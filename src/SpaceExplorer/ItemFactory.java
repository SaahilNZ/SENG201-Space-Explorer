package SpaceExplorer;

public class ItemFactory {
	private static int itemId = 0;
	
	public static MedicalItem createMedicalItem(String name, int price,
			boolean canBeFound, boolean canBeSold, int restoreAmount, boolean curesPlague) {
		int id = itemId;
		itemId++;
		return new MedicalItem(id, name, price, canBeFound, canBeSold, restoreAmount, curesPlague);
	}
}
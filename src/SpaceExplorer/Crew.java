package SpaceExplorer;

import java.util.ArrayList;

public class Crew {
	private ArrayList<CrewMember> crewMembers;
	private String crewName;
	private Ship ship;
	private ArrayList<Item> items;
	private int money;
	private int shipPiecesFound;
	
	public Crew(ArrayList<CrewMember> crewMembers, String crewName, Ship ship,
			ArrayList<MedicalItem> startingMedItems, ArrayList<FoodItem> startingFoodItems,
			int startingMoney) {
		this.crewMembers = crewMembers;
		this.crewName = crewName;
		this.ship = ship;
		this.items = new ArrayList<Item>();
		this.items.addAll(startingFoodItems);
		this.items.addAll(startingMedItems);
		this.money = startingMoney;
		this.shipPiecesFound = 0;
	}
	
	public int deductMoney(int amount) {
		money = Math.max(0, money - amount);
		return money;
	}
	
	public int receiveMoney(int amount) {
		money = money + amount;
		return money;
	}
	
	public int getShipPieces() {
		return shipPiecesFound;
	}
	
	public String getCrewName() {
		return crewName;
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public Ship getShip() {
		return ship;
	}
	
	public ArrayList<CrewMember> getCrewMembers() {
		return crewMembers;
	}
}

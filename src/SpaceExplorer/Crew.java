package SpaceExplorer;

import SpaceExplorer.CrewMembers.CrewMember;

import java.util.ArrayList;
import java.util.Collection;

public class Crew {
	private ArrayList<CrewMember> crewMembers;
	private String crewName;
	private Ship ship;
	private ArrayList<Item> items;
	private int money;
	private int shipPiecesFound;
	
	public Crew(Collection<CrewMember> crewMembers, String crewName, Ship ship,
				int startingMoney, Collection<Item> startingItems) {
		this.crewMembers = new ArrayList<>();
		this.crewMembers.addAll(crewMembers);
		this.crewName = crewName;
		this.ship = ship;
		this.items = new ArrayList<>();
		if (startingItems != null) {
			this.items.addAll(startingItems);
		}
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
	
	public int currentMoney() {
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
	
	public void removeMember(CrewMember member) {
		crewMembers.remove(member);
	}
}

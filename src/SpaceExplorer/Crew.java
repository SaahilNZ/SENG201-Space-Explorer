package SpaceExplorer;

import SpaceExplorer.CrewMembers.CrewMember;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class implements a crew object that stores all the crew members, ship, money, items and ship pieces found
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 *
 */
public class Crew {
	private ArrayList<CrewMember> crewMembers;
	private String crewName;
	private Ship ship;
	private ArrayList<Item> items;
	private int money;
	private int shipPiecesFound;
	
	/**
	 * Constructor for the crew class. 
	 * 
	 * @param crewMembers			An ArrayList containing the crew members
	 * @param crewName				The name of the crew
	 * @param ship					The crew's ship
	 * @param startingMoney			The amount of money the crew starts with
	 * @param startingItems			The items the crew starts with
	 */
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
	
	/**
	 * Deducts money from the crew
	 * 
	 * @param amount			The amount of money deducted
	 * @return					The maximum of 0 or remaining money after the amount has been deducted
	 */
	public int deductMoney(int amount) {
		money = Math.max(0, money - amount);
		return money;
	}
	
	/**
	 * Gives money to the crew
	 * 
	 * @param amount			The amount of money given
	 * @return					The crews total money
	 */
	public int receiveMoney(int amount) {
		money = money + amount;
		return money;
	}
	
	/**
	 * Getter method for the crew's money
	 * 
	 * @return					The crew's current money
	 */
	public int currentMoney() {
		return money;
	}
	
	/**
	 * Getter for the number of ship pieces found by a crew
	 * 
	 * @return					The number of ship pieces found
	 */
	public int getShipPieces() {
		return shipPiecesFound;
	}
	
	/**
	 * Getter method for the crew's name
	 * 
	 * @return					The name of the crew
	 */
	public String getCrewName() {
		return crewName;
	}
	
	/**
	 * Getter method for the items held by a crew
	 * 
	 * @return					An ArrayList containing the items held by the crew
	 */
	public ArrayList<Item> getItems() {
		return items;
	}
	
	/**
	 * Getter method for the crew's ship
	 * 
	 * @return					The crew's ship
	 */
	public Ship getShip() {
		return ship;
	}
	
	/**
	 * Getter method for the members in the crew
	 * 
	 * @return					An ArrayList containing the crew members
	 */
	public ArrayList<CrewMember> getCrewMembers() {
		return crewMembers;
	}
	
	/**
	 * Removes a crew member from the crew
	 * 
	 * @param member			The crew member to be removed
	 */
	public void removeMember(CrewMember member) {
		crewMembers.remove(member);
	}
	
	/**
	 * Runs a check to see if the health of any crew members has dropped to 0. Removes them from the crew
	 * if that is the case
	 * 
	 * @return					The number of crew members remaining
	 */
	public int pruneCrewMembers() {
		crewMembers.removeIf(crewMember -> crewMember.getHealth() <= 0);
		return crewMembers.size();
	}
	
	/**
	 * Modifies the health, hunger and actions remaining of a crew member when a new day is called.
	 * Calls the pruneCrewMembers method to remove any dead crew members. 
	 * 
	 * @return					The size of the crew after dead members have been removed
	 */
	public int newDay() {
		for (CrewMember crewMember : this.crewMembers) {
			crewMember.modifyHunger(20);
			if (crewMember.hasPlague()) {
				crewMember.modifyHealth(-20);
			}
			if (crewMember.getHunger() >= crewMember.getMaxHunger()) {
				crewMember.modifyHealth(-20);
			}
			crewMember.setActions(crewMember.getTiredness() < crewMember.getMaxTiredness() ? 2 : 1);
		}
		return pruneCrewMembers();
	}
	
	/**
	 * Increases the number of ship parts found by 1
	 */
	public void findShipPiece() {
		shipPiecesFound += 1;
	}
	
	/**
	 * Adds an item to the crew
	 * 
	 * @param item				The item being added to the crew
	 */
	public void addItem(Item item) {
		items.add(item);
	}
	
	/**
	 * Removes an item from the crew
	 * 
	 * @param item				The item being removed
	 * @return					True if the item was in the list
	 */
	public boolean removeItem(Item item) {
		return items.remove(item);
	}
}

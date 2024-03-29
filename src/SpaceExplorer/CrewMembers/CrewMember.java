package SpaceExplorer.CrewMembers;

import java.util.ArrayList;
import java.util.Random;

import SpaceExplorer.Crew;
import SpaceExplorer.FoodItem;
import SpaceExplorer.Game;
import SpaceExplorer.Item;
import SpaceExplorer.MedicalItem;
import SpaceExplorer.Planet;
import SpaceExplorer.Ship;

/**
 * This class implements a crew member and their associated functionality.
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 *
 */
public abstract class CrewMember {
	public static final int DEFAULT_HEALTH = 100;
	public static final int DEFAULT_HUNGER = 100;
	public static final int DEFAULT_TIREDNESS = 100;
	public static final int SHIP_PART_CHANCE = 40;
	public static final int MONEY_CHANCE = 20;
	public static final int ITEM_CHANCE = 20;

	private String crewClass;
	private String name;
	private int health;
	private int maxHealth;
	private int hunger;
	private int maxHunger;
	private int tiredness;
	private int maxTiredness;
	private int actionsLeft = 2;
	private boolean hasPlague = false;
	
	/**
	 * A basic constructor for the CrewMember class
	 * 
	 * @param name					Name of the crew member
	 * @param crewClass				Class of the crew member
	 * @param maxHealth				The crew member's max health
	 * @param maxHunger				The crew member's max hunger
	 * @param maxTiredness			The crew member's max tiredness
	 */
	public CrewMember(String name, String crewClass, int maxHealth,
		int maxHunger, int maxTiredness) {
		this.name = name;
		this.crewClass = crewClass;
		this.maxHealth = maxHealth;
		this.health = maxHealth;
		this.maxHunger = maxHunger;
		this.hunger = 0;
		this.maxTiredness = maxTiredness;
		this.tiredness = 0;
	}
	
	/**
	 * Getter function for the crew member's health
	 * 
	 * @return				The crew member's current health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Getter function for the crew member's maximum health
	 * 
	 * @return				The crew member's max health
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	
	/**
	 * Getter functions for the crew member's name
	 * 
	 * @return				The crew member's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter function for the crew member's hunger
	 * 
	 * @return				The crew member's hunger level
	 */
	public int getHunger() {
		return hunger;
	}
	
	/**
	 * Getter function for the crew member's maximum hunger
	 * 
	 * @return				The crew member's max hunger level
	 */
	public int getMaxHunger() {
		return maxHunger;
	}
	
	/**
	 * Getter function for the crew member's tiredness
	 * 
	 * @return				The crew member's level of tiredness
	 */
	public int getTiredness() {
		return tiredness;
	}
	
	/**
	 * Getter function for the crew member's maximum tiredness
	 * 
	 * @return				The crew member's max level of tiredness
	 */
	public int getMaxTiredness() {
		return maxTiredness;
	}
	
	/**
	 * Getter function for the number of actions the crew member has remaining
	 * 
	 * @return				The crew member's remaining actions
	 */
	public int getActions() {
		return actionsLeft;
	}
	
	/**
	 * Getter function for whether the crew member has the plague or not
	 * 
	 * @return				True if the crew member has the plague, false otherwise
	 */
	public boolean hasPlague() {
		return hasPlague;
	}
	
	
	/**
	 * Reduces the number of actions the crew member has remaining
	 * Also increases the crew member's tiredness
	 * 
	 */
	public boolean takeAction() {
		if (actionsLeft > 0) {
			actionsLeft -= 1;
			modifyTiredness(20);
			modifyHunger(20);
			return true;
		}
		return false;
	}
	/**
	 * The crew member sleeps, restoring their tiredness levels to full.
	 * 
	 * @return 						A message stating what has occurred
	 */
	public ActionResult sleep() {
		String message = "";
		boolean success = false;
		if (takeAction()) {
			message += getName() + " has rested, and is no longer tired.";
			tiredness = 0;
			success = true;
		} else {
			message += getName() + " does not have enough actions left to sleep.";
		}
		return new ActionResult(message, success);
	}
	
	/**
	 * Modifies the crew member's health
	 * 
	 * @param amount				Amount to modify the crew member's health by
	 */
	public void modifyHealth(int amount) {
		health = Math.max(0, Math.min(maxHealth, health+amount));
	}
	
	/**
	 * Sets the crew member's plague status. Does damage to the crew member if they get the plague
	 * 
	 */
	public void setPlague(boolean status) {
		this.hasPlague = status;
	}
	
	/**
	 * Modifies the crew member's tiredness
	 * 
	 * @param amount				Amount to modify the crew member's tiredness levels by
	 */
	public void modifyTiredness(int amount) {
		tiredness = Math.max(0, Math.min(tiredness + amount, maxTiredness));
	}

	/**
	 * Modifies the crew member's hunger
	 * 
	 * @param amount				Amount to modify the crew member's hunger levels by
	 */
	public void modifyHunger(int amount) {
		hunger = Math.max(0,Math.min(maxHunger, hunger + amount));
	}
	
	/**
	 * Uses an item on the crew member.
	 * If the item is a medical item, it restores health and cures plague based on
	 * what the medical item can do.
	 * If the item is a food item, it restores hunger.
	 * 
	 * @param item					An item to use on the crew member
	 */
	public ActionResult useItem(Item item) {
		String message = "";
		boolean success = false;
		if (actionsLeft > 0) {
			actionsLeft -= 1;
			if (item instanceof MedicalItem) {				
				message += getName() + " has used the " + item.getName() + ".";
				modifyHealth(((MedicalItem)item).getRestoreAmount());
				if (((MedicalItem)item).curesPlague()) {
					setPlague(false);
				}
			} else if (item instanceof FoodItem) {
				message += getName() + " has eaten the " + item.getName() + ".";
				modifyHunger(-((FoodItem)item).getHungerAmount());
				modifyTiredness(-((FoodItem)item).getTiredAmount());
			}
			success = true;
		} else {
			message += getName() + " does not have enough actions to use an item.";
		}
		return new ActionResult(message, success);
	}
	
	/**
	 * Repairs the ships health and shield levels
	 * 
	 * @param ship				The ship the crew members are in
	 * @return					A message stating what has occurred
	 */
	public ActionResult repairShip(Ship ship) {
		String message = "";
		boolean success = false;
		if (takeAction()) {
			ship.repairShield(20);
			ship.repairShip(10);
			message += getName() + " has repaired the " + ship.toString() + ".\n";
			message += "The " + ship.toString() + "'s shields have been restored by 20 points.\n";
			message += "The " + ship.toString() + "'s health has been restored by 10 points.\n";
			success = true;
		} else {
			message += getName() + " does not have enough actions left to repair the ship.";
		}
		return new ActionResult(message, success);
	}
	
	/**
	 * Searches the current planet and has a random chance of giving the player a 
	 * ship part, food/medical item, money or nothing
	 * 
	 * @param crew					The crew to add found items to
	 * @param planet				The current planet
	 */
	public ActionResult searchPlanet(Crew crew, Planet planet) {
		String message = "";
		boolean success = false;
		if (takeAction()) {
			message += getName() + " searches " + planet.toString() + ":\n";
			message += searchPlanetOnce(crew, planet);
		} else {
			message += getName() + " doesn't have enough actions left to search the planet.";
		}
		return new ActionResult(message, success);
	}
	
	/**
	 * Searches the current planet. Only called indirectly through the searchPlanet method
	 * 
	 * @param crew					The crew to add fount items to
	 * @param planet				The planet to search
	 * @return						A message based on what was found
	 */
	protected String searchPlanetOnce(Crew crew, Planet planet) {
		String message = "";
		
		Random random = new Random();
		int search = random.nextInt(100);
		if (planet.hasShipPart()) {
			if (search < SHIP_PART_CHANCE) {
				planet.findShipPart();
				crew.findShipPiece();
				message += "Found a ship part!\n";
			} else if (search < SHIP_PART_CHANCE + ITEM_CHANCE) {
				ArrayList<Item> items = new ArrayList<Item>();
				for (Item item : Game.getCurrentGame().getItems()) {
					if (item.canBeFound()) {
						items.add(item);
					}
				}
				Item item = items.get(random.nextInt(items.size()));
				Game.getCurrentGame().getCrew().addItem(item);
				message += "Found an item: " + item.getName() + "\n";
			} else if (search < SHIP_PART_CHANCE + ITEM_CHANCE + MONEY_CHANCE) {
				int money = 20 + random.nextInt(81);
				crew.receiveMoney(money);
				message += "Found $" + money + ".\n";
			} else {
				message += "Nothing found from this search.\n";
			}
		} else {
			if (search < SHIP_PART_CHANCE + ITEM_CHANCE) {
				ArrayList<Item> items = new ArrayList<Item>();
				for (Item item : Game.getCurrentGame().getItems()) {
					if (item.canBeFound()) {
						items.add(item);
					}
				}
				Item item = items.get(random.nextInt(items.size()));
				Game.getCurrentGame().getCrew().addItem(item.createCopy());
				message += "Found an item: " + item.getName() + "\n";
			} else if (search < SHIP_PART_CHANCE + ITEM_CHANCE + MONEY_CHANCE) {
				int money = 20 + random.nextInt(81);
				crew.receiveMoney(money);
				message += "Found $" + money + ".\n";
			} else {
				message += "Nothing found from this search.\n";
			}
		}
		
		return message;
	}
	
	/**
	 * Pilots the ship to a new planet. Takes 2 crew members to do so
	 * 
	 * @return						Returns whether or not the action was successful
	 */
	public ActionResult pilotShip() {
		return new ActionResult("", takeAction());
	}
	
	/**
	 * Checks a crew member has enough actions left to pilot a ship
	 * 
	 * @return				True or false base on whether the crew member has enough actions
	 */
	public boolean canPilotShip() {
		return actionsLeft > 0;
	}
	
	/**
	 * Sets the amount of actions the crew member can take
	 * 
	 * @param actions				Amount of actions
	 */
	public void setActions(int actions) {
		this.actionsLeft = actions;
	}

	@Override
	public String toString() {
		return name + " - " + crewClass;
	}
	
	/**
	 * This nested class is used for keeping track of a message and whether the action associated with it was a success
	 * 
	 * @author Saahil Hari and Isaac Walton
	 * @version 1.0, May 2019
	 *
	 */
	public class ActionResult {
		private String message;
		private boolean success;
		public ActionResult(String message, boolean success) {
			this.message = message;
			this.success = success;
		}
		
		/**
		 * Getter method for the message
		 * 
		 * @return
		 */
		public String getMessage() {
			return message;
		}
		
		/**
		 * Getter method for if the action was a success
		 * 
		 * @return
		 */
		public boolean getSuccess() {
			return success;
		}
		
		@Override
		public String toString() {
			return this.message;
		}
	}
}

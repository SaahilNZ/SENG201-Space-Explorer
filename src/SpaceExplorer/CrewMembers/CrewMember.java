package SpaceExplorer.CrewMembers;

import SpaceExplorer.FoodItem;
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
		this.hunger = maxHunger;
		this.maxTiredness = maxTiredness;
		this.tiredness = maxTiredness;
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
	 * 
	 */
	public void takeAction() {
		actionsLeft -= 1;
	}
	
	/**
	 * Does damage to the crew member by reducing their health. 
	 * Kills the crew member if their health drops below 0
	 * 
	 * @param damage				Amount of damage dealt to the crew member
	 */
	public void damageCrew(int damage) {
		health = Math.max(0, health-damage);
	}
	
	/**
	 * The crew member sleeps, restoring their tiredness levels to full.
	 * 
	 */
	public void sleep() {
		if (actionsLeft > 0){
			tiredness = maxTiredness;
			actionsLeft -= 1;
		}
	}
	
	/**
	 * Restores the crew members health
	 * 
	 * @param restore				Amount of health restored to the crew member
	 */
	public void heal(int restore) {
		health = Math.min(maxHealth, health+restore);
	}
	
	/**
	 * The crew member consumes a food item and restores their hunger
	 * 
	 * @param food				Amount of hunger restored to the crew member
	 */
	public void eat(FoodItem food) {
		if (actionsLeft > 0) {
			hunger = Math.min(maxHunger, hunger + food.getHungerAmount());
			tiredness = Math.min(maxTiredness, tiredness + food.getTiredAmount());
			actionsLeft -= 1;
		}
	}
	
	/**
	 * Sets the crew member's plague status. Does damage to the crew member if they get the plague
	 * 
	 */
	public void setPlague(boolean status) {
		this.hasPlague = status;
	}
	
	/**
	 * Prints the crew member's status
	 */
	public void viewStatus() {
		//For command line application
		System.out.println(name + "'s status:");
		System.out.println("Class: " + crewClass);
		System.out.println("Health: " + health + "%");
		System.out.println("Hunger: " + hunger + "%");
		System.out.println("Tiredness: " + tiredness + "%");
		System.out.println("Actions left: " + actionsLeft);
	}
	
	/**
	 * Increases how tired the crew member is
	 * 
	 * @param exhaustion				Amount the crew member's tiredness levels are reduced by
	 */
	public void becomeTired(int exhaustion) {
		tiredness = Math.max(0, tiredness-exhaustion);
	}
	
	/**
	 * Increases how hungry the crew member is
	 * 
	 * @param hungeryness				Amount the crew member's hunger levels are reduces by
	 */
	public void becomeHungry(int hungeryness) {
		hunger = Math.max(0, hunger-hungeryness);
	}
	
	/**
	 * Uses a medical item on the crew member. Restores health and cures plague based on
	 * what the medical item can do.
	 * 
	 * @param item					An item of the MedicalItem class
	 */
	public void useItem(MedicalItem item) {
		if (actionsLeft>0) {
			actionsLeft -= 1;
			health += item.getRestoreAmount();
			if (item.curesPlague()) {
				hasPlague = false;
			}
		}
	}
	
	/**
	 * Repairs the ships health and shield levels
	 * 
	 * @param ship				The ship the crew members are in
	 */
	public void repairShip(Ship ship) {
		if (actionsLeft>0) {
			actionsLeft -= 1;
			ship.repairShield(20);
			ship.repairShip(10);
		}
	}
	
	/**
	 * Searches the current planet and has a random chance of giving the player a 
	 * ship part, food/medical item, money or nothing
	 * 
	 * @param planet				The current planet
	 */
	public void searchPlanet(Planet planet) {
		//Search a planet
	}
	
	/**
	 * Pilots the ship to a new planet. Takes 2 crew members to do so
	 * 
	 */
	public void pilotShip() {
		//Pilot the ship. Requires 2 actions and another pilot
	}

	@Override
	public String toString() {
		return name + " - " + crewClass;
	}
}

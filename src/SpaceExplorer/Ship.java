package SpaceExplorer;

/**
 * This class implements a ship for the crew
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 *
 */
public class Ship {
	private String name;
	private int health;
	private int maxHealth;
	private int shield;
	private int maxShield;
	
	/**
	 * Constructor for the ship
	 * 
	 * @param name			Name of the ship
	 * @param maxHealth		The ship's max health
	 * @param maxShield		The ship's max shield
	 */
	public Ship(String name, int maxHealth, int maxShield) {
		this.name = name;
		this.maxHealth = maxHealth;
		this.health = maxHealth;
		this.maxShield = maxShield;
		this.shield = maxShield;
	}
	
	/**
	 * Getter method for the ship's name
	 * @return			The name of the ship
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Method to damage the ship's health
	 * @param amount	Damage done to the ship
	 * @return			The ship's remaining health
	 */
	public int damageShip(int amount) {
		health = Math.max(0, health - amount);
		return health;
	}
	
	/**
	 * Method to restore the ship's health
	 * @param amount	Health restored to the ship
	 * @return			The ships health after repair
	 */
	public int repairShip(int amount) {
		health = Math.min(maxHealth, health + amount);
		return health;
	}
	
	/**
	 * Method to damage the ship's shield
	 * @param amount	Damage done to the ship's shield
	 * @return			The ship's current shield level
	 */
	public int damageShield(int amount) {
		shield = Math.max(0, shield - amount);
		return shield;
	}
	
	/**
	 * Method to repair the ship's shield
	 * @param amount	Amount restored to the ship's shield
	 * @return			The ship's shield level after repair
	 */
	public int repairShield(int amount) {
		shield = Math.min(maxShield, shield + amount);
		return shield;
	}
	
	/**
	 * Getter method for the ship's shield
	 * @return			The ship's shield level
	 */
	public int getShieldLevel() {
		return shield;
	}
	
	/**
	 * Getter method for the ship's health
	 * @return			The ship's current health level
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * A string description of the ship's details for the player. Displays current health/ shield levels alongside
	 * their maximum values
	 * @return			The ship's details
	 */
	public String getStatus() {
		String output = "Health: " + health + "/" + maxHealth + "\n";
		output += "Shield: " + shield + "/" + maxShield + "\n";
		return output;
	}
	
	@Override
	public String toString() {
		return "Starship " + name;
	}
}

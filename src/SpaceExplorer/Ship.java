package SpaceExplorer;

public class Ship {
	private String name;
	private int health;
	private int maxHealth;
	private int shield;
	private int maxShield;
	
	public Ship(String name, int maxHealth, int maxShield) {
		this.name = name;
		this.maxHealth = maxHealth;
		this.health = maxHealth;
		this.maxShield = maxShield;
		this.shield = maxShield;
	}
	
	public String getName() {
		return name;
	}
	
	public int damageShip(int amount) {
		health = Math.max(0, health - amount);
		return health;
	}
	
	public int repairShip(int amount) {
		health = Math.min(maxHealth, health + amount);
		return health;
	}

	public int damageShield(int amount) {
		shield = Math.max(0, shield - amount);
		return shield;
	}

	public int repairShield(int amount) {
		shield = Math.min(maxShield, shield + amount);
		return shield;
	}
	
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

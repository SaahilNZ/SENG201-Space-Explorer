package SpaceExplorer;

public class Ship {
	private String name;
	private int health;
	private int maxHealth;
	
	public Ship(String name, int maxHealth) {
		this.name = name;
		this.maxHealth = maxHealth;
		this.health = maxHealth;
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
	
	public void viewStatus() {
		System.out.println("Starship " + name);
		System.out.println("Health: " + health + "/" + maxHealth);
		System.out.println();
	}
	
	@Override
	public String toString() {
		return name;
	}
}

package SpaceExplorer.CrewMembers;

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
	
	public int getHealth() {
		return health;
	}
	
	public String getName() {
		return name;
	}
	
	public int getHunger() {
		return hunger;
	}
	
	public int getTiredness() {
		return tiredness;
	}
	
	public int getActions() {
		return actionsLeft;
	}
	
	public boolean hasPlague() {
		return hasPlague;
	}
	
	public void damageCrew(int damage) {
		health -= damage;
	}
	
	public void sleep() {
		tiredness = maxTiredness;
	}
	
	public void heal(int restore) {
		health += restore;
		if(health >= maxHealth) {
			health = maxHealth;
		}
	}
	
	public void eat(int food) {
		hunger += food;
		if(hunger >= maxHunger) {
			hunger = maxHunger;
		}
	}
	
	public void setPlague(boolean status) {
		hasPlague = status;
	}
	
	public void viewStatus() {
		//For command line application
		System.out.println(name + "'s status:");
		System.out.println("Health: " + health + "%");
		System.out.println("Hunger: " + hunger + "%");
		System.out.println("Tiredness: " + tiredness + "%");
		System.out.println("Actions left: " + actionsLeft);
	}
	
	public void use() {
		//Use an item (both medical and food)
	}
	
	public void repairShip() {
		//Repair the ship
	}
	
	public void searchPlanet() {
		//Search a planet
	}
	
	public void pilotShip() {
		//Pilot the ship. Requires 2 actions and another pilot
	}
	
	public void visitOutpost() {
		//Visits an outpost
	}
	
	public void purchaseItem() {
		//Placeholder method
	}

	@Override
	public String toString() {
		return name + " - " + crewClass;
	}
}

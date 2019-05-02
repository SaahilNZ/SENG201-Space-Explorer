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
	private boolean isAlive = true;
	
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
	
	public boolean alive() {
		return isAlive;
	}
	
	public void damageCrew(int damage) {
		health -= damage;
		if (health <= 0) {
			health = 0;
			isAlive = false;
		}
	}
	
	public void sleep() {
		if (actionsLeft > 0){
			tiredness = maxTiredness;
			actionsLeft -= 1;
		}else {
			System.out.println(name + " is out of actions for the day");
		}
	}
	
	public void heal(int restore) {
		if (isAlive) {
			health += restore;
			if(health >= maxHealth) {
				health = maxHealth;
			}
		}else {
			System.out.println(name + " is dead");
		}
	}
	
	public void eat(int food) {
		if (actionsLeft > 0) {
			hunger += food;
			actionsLeft -= 1;
			if(hunger >= maxHunger) {
				hunger = maxHunger;
			}
		}else {
			System.out.println(name + " is out of actions for the day");
		}
	}
	
	public void setPlague(boolean status) {
		hasPlague = status;
		if(status) {
			damageCrew(10);
		}
	}
	
	public void viewStatus() {
		//For command line application
		System.out.println(name + "'s status:");
		System.out.println("Class: " + crewClass);
		System.out.println("Health: " + health + "%");
		System.out.println("Hunger: " + hunger + "%");
		System.out.println("Tiredness: " + tiredness + "%");
		System.out.println("Actions left: " + actionsLeft);
		if(isAlive) {System.out.println("They are alive");
		}else {System.out.println("They are dead");}
	}
	
	public void becomeTired(int exhaustion) {
		tiredness -= exhaustion;
		if (tiredness<0) {tiredness = 0;}
	}
	
	public void becomeHungry(int hungeryness) {
		hunger -= hungeryness;
		if(hunger<0) {hunger = 0;}
	}
	
	public void useItem() {
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

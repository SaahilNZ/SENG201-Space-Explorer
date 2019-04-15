package SpaceExplorer;

public class CrewMember {
	private String name;
	private int health = 100;
	private int hunger = 100;
	private int tiredness = 100;
	private int actionsLeft = 2;
	
	public CrewMember(String memberName) {
		name = memberName;
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
	
	public void damageCrew(int damage) {
		health -= damage;
	}
	
	public void sleep() {
		tiredness = 100;
	}
	
	public void heal(int restore) {
		health += restore;
		if(health >= 100) {
			health = 100;
		}
	}
	
	public void eat(int food) {
		hunger += food;
		if(hunger >= 100) {
			hunger = 100;
		}
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
	
}

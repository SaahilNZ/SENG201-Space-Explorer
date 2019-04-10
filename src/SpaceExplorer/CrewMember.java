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
	
	public void damageCrew(int damage) {
		health -= damage;
	}
	
	public void sleep() {
		tiredness = 100;
	}
	
	public int getHealth() {
		return health;
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
		//Placeholder method
	}
	
	public void repairShip() {
		//Placeholder method
	}
	
	public void searchPlanet() {
		//Placeholder method
	}
	
	public void pilotShip() {
		//Placeholder method
	}
	
	public void visitOutpost() {
		//Placeholder method
	}
	
	public void purchaseItem() {
		//Placeholder method
	}
	
}

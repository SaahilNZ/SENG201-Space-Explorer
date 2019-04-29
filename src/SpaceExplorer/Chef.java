package SpaceExplorer;

public class Chef extends CrewMember{
	private int hunger = 120;
	private int maxHunger = 120;
	
	public Chef(String memberName) {
		super(memberName);
	}
	
	public void cook(FoodItem food) {
		food.cookFood();
	}
	
}

package SpaceExplorer;

import java.util.ArrayList;

public class Game {
	private static Game instance;
	
	private ArrayList<Planet> planets;
	
	private Game() {
		planets = new ArrayList<Planet>();
	}
	
	public static Game getCurrentGame() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		System.out.println("Space Explorer");
	}
}
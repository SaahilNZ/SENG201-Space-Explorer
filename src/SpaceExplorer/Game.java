package SpaceExplorer;

import java.util.ArrayList;

import SpaceExplorer.Utilities.NameGenerator;
import SpaceExplorer.Utilities.StaticData;

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
		NameGenerator planetNameGenerator = new NameGenerator(StaticData.PLANET_PREFIXES,
				StaticData.PLANET_NAMES, StaticData.PLANET_SUFFIXES);
		
		Game game = Game.getCurrentGame();
		for (int i = 0; i < 10; i++) {
			game.planets.add(new Planet(planetNameGenerator.generateName()));
		}
		
		System.out.println("Space Explorer");
		System.out.println();
		System.out.println("Planets:");
		
		for (Planet planet : game.planets) {
			System.out.println(planet.toString());
		}
	}
}
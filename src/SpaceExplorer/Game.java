package SpaceExplorer;

import java.util.ArrayList;

import SpaceExplorer.Utilities.NameGenerator;
import SpaceExplorer.Utilities.StaticData;

public class Game {
	private static Game instance;
	
	private ArrayList<Planet> planets;
	private ArrayList<Item> allItems;
	
	private Game() {
		planets = new ArrayList<Planet>();
		allItems = new ArrayList<Item>();
	}
	
	public static Game getCurrentGame() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}
	
	public ArrayList<Item> getItems() {
		return allItems;
	}
	
	private void startGame() {
		NameGenerator planetNameGenerator = new NameGenerator(StaticData.PLANET_PREFIXES,
				StaticData.PLANET_NAMES, StaticData.PLANET_SUFFIXES);
		
		NameGenerator outpostNameGenerator = new NameGenerator(null, StaticData.OUTPOST_NAMES,
				StaticData.OUTPOST_SUFFIXES, false, true, 0, 100);
		
		for (int i = 0; i < 10; i++) {
			Outpost outpost = new Outpost(outpostNameGenerator.generateName());
			planets.add(new Planet(planetNameGenerator.generateName(), outpost));
		}
		
		System.out.println("Space Explorer");
		System.out.println();
		System.out.println("Planets:");
		
		for (Planet planet : planets) {
			System.out.println(planet.toString());
		}
	}
	
	public static void main(String[] args) {
		Game game = Game.getCurrentGame();
		game.startGame();
	}
}
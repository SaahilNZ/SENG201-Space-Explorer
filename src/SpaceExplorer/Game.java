package SpaceExplorer;

import java.util.ArrayList;
import java.util.Scanner;

import SpaceExplorer.Utilities.NameGenerator;
import SpaceExplorer.Utilities.StaticData;

public class Game {
	private static Game instance;
	
	private ArrayList<Planet> planets;
	private ArrayList<Item> allItems;
	private int desiredDays;
	
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
		System.out.println("Space Explorer");
		System.out.println();

		selectDays();
		generatePlanets();

		System.out.println("Planets:");
		
		for (Planet planet : planets) {
			System.out.println(planet.toString());
		}
	}

	private void selectDays() {
		Scanner scanner = new Scanner(System.in);
		int days = -1;
		System.out.println("How many days would you like the game to last?");
		while (days < 3 || days > 10) {
			try {
				System.out.print("(Min. 3, Max. 10): ");
				String daysStr = scanner.next();
				days = Integer.parseInt(daysStr);
				if (days < 3 || days > 10) {
					System.out.println("Please enter a valid number between 3 and 10.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid number between 3 and 10.");
			}
		}
		scanner.close();
		this.desiredDays = days;
	}

	private void generatePlanets() {
		NameGenerator planetNameGenerator = new NameGenerator(StaticData.PLANET_PREFIXES,
				StaticData.PLANET_NAMES, StaticData.PLANET_SUFFIXES);

		NameGenerator outpostNameGenerator = new NameGenerator(null, StaticData.OUTPOST_NAMES,
				StaticData.OUTPOST_SUFFIXES, false, true, 0, 100);

		int numPlanets = (this.desiredDays * 2) / 3;
		for (int i = 0; i < numPlanets; i++) {
			Outpost outpost = new Outpost(outpostNameGenerator.generateName());
			planets.add(new Planet(planetNameGenerator.generateName(), outpost));
		}
	}
	
	public static void main(String[] args) {
		Game game = Game.getCurrentGame();
		game.startGame();
	}
}
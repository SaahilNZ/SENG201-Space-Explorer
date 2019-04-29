package SpaceExplorer;

import java.util.ArrayList;
import java.util.Scanner;

import SpaceExplorer.Utilities.NameGenerator;
import SpaceExplorer.Utilities.StaticData;

public class Game {
	private static Game instance;
	
	private ArrayList<Planet> planets;
	private ArrayList<Item> allItems;
	private Crew crew;
	private int desiredDays;
	private int currentDay = 0;
	
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

	public int getCurrentDay() {
		return currentDay;
	}
	
	private void startGame() {
		System.out.println("Space Explorer");
		System.out.println();

		selectDays();
		generatePlanets();
		createCrew();

		System.out.println();
		System.out.println("Planets:");
		
		for (Planet planet : planets) {
			System.out.println(planet.toString());
		}

		newDay();
	}

	private void selectDays() {
		Scanner scanner = new Scanner(System.in);
		int days = -1;
		System.out.println("How many days would you like the game to last?");
		while (days < 3 || days > 10) {
			try {
				System.out.print("(Min. 3, Max. 10): ");
				String daysStr = scanner.nextLine().trim();
				days = Integer.parseInt(daysStr);
				if (days < 3 || days > 10) {
					System.out.println("Please enter a valid number between 3 and 10.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid number between 3 and 10.");
			}
		}
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

	private void createCrew() {
		Scanner scanner = new Scanner(System.in);

		String crewName = "";
		boolean inputValid = false;
		System.out.println("Please enter a crew name:");
		while (!inputValid) {
			System.out.print("Crew name: ");
			crewName = scanner.nextLine().trim();
			if (crewName.isEmpty()) {
				System.out.println("Please enter a valid crew name.");
			} else {
				System.out.println("Your crew will be called '" + crewName + "'. Is this correct?");
				System.out.print("(Y/N): ");
				String confirm = scanner.nextLine().trim().toLowerCase();
				if (confirm.equals("y") || confirm.equals("yes")) {
					inputValid = true;
				}
			}
		}

		String shipName = "";
		inputValid = false;
		System.out.println("Please enter a ship name:");
		while (!inputValid) {
			System.out.print("Ship name: ");
			shipName = scanner.nextLine().trim();
			if (shipName.isEmpty()) {
				System.out.println("Please enter a valid ship name.");
			} else {
				System.out.println("Your ship will be called the Starship " + shipName + ". Is this correct?");
				System.out.print("(Y/N): ");
				String confirm = scanner.nextLine().trim().toLowerCase();
				if (confirm.equals("y") || confirm.equals("yes")) {
					inputValid = true;
				}
			}
		}
	}

	private void newDay() {
		this.currentDay += 1;
	}

	private void quitGame() {
		System.exit(0);
	}
	
	public static void main(String[] args) {
		Game game = Game.getCurrentGame();
		game.startGame();
	}
}
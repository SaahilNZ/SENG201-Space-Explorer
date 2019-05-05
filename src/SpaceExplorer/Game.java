package SpaceExplorer;

import java.util.ArrayList;
import java.util.Scanner;

import SpaceExplorer.CrewMembers.*;
import SpaceExplorer.Utilities.NameGenerator;
import SpaceExplorer.Utilities.StaticData;

public class Game {
	private static final int STARTING_MONEY = 200;

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
		System.out.println();
		generatePlanets();
		createCrew();

		System.out.println("Crew Members:");
		for (CrewMember crewMember : crew.getCrewMembers()) {
			System.out.println(crewMember.toString());
		}
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
		System.out.println("Crew Setup:\n");
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
		System.out.println();

		Ship ship = createShip(scanner);
		ArrayList<CrewMember> crewMembers = createCrewMembers(scanner);


		this.crew = new Crew(crewMembers, crewName, ship, STARTING_MONEY, null);
	}

	private Ship createShip(Scanner scanner) {
		String shipName = "";
		boolean inputValid = false;
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
			System.out.println();
		}
		return new Ship(shipName, 200, 200);
	}

	private ArrayList<CrewMember> createCrewMembers(Scanner scanner) {
		ArrayList<CrewMember> crewMembers = new ArrayList<>();
		int crewCount = -1;
		while (crewCount < 2 || crewCount > 4) {
			try {
				System.out.println("How many crew members would you like to have?");
				System.out.print("(Min. 2, Max. 4): ");
				String crewStr = scanner.nextLine().trim();
				crewCount = Integer.parseInt(crewStr);
				if (crewCount < 2 || crewCount > 4) {
					System.out.println();
					System.out.println("Please enter a valid number between 2 and 4.");
				}
				System.out.println();
			} catch (NumberFormatException e) {
				System.out.println();
				System.out.println("Please enter a valid number between 2 and 4.\n");
			}
		}

		System.out.println("Crew Member Setup:\n");
		while (crewMembers.size() < crewCount) {
			System.out.println("Please make a selection:");
			System.out.println("1 - Create a crew member");
			System.out.println("2 - Help");
			System.out.print("(Selection): ");
			String selection = scanner.nextLine().trim();
			System.out.println();
			switch (selection) {
				case "1":
					CrewMember crewMember = createCrewMember(scanner);
					if (crewMember != null) {
						crewMembers.add(crewMember);
					}
					break;
				case "2":
					displayCreateCrewHelp(scanner);
					break;
				default:
					System.out.println("Invalid option entered.\n");
					break;
			}
		}

		return crewMembers;
	}

	private CrewMember createCrewMember(Scanner scanner) {
		CrewMember crewMember = null;
		boolean isValid = false;
		while (!isValid) {
			System.out.println("Crew Member Types:\n");
			System.out.println("1 - Space Marine");
			System.out.println("2 - Scout");
			System.out.println("3 - Mechanic");
			System.out.println("4 - Doctor");
			System.out.println("5 - Chef");
			System.out.println("6 - Space Bard");
			System.out.print("(Selection): ");
			String selection = scanner.nextLine().trim();
			if ("123456".contains(selection)) {
				System.out.println("\nWhat do you want to name your new crew member?");
				System.out.print("(Name): ");
				String name = scanner.nextLine().trim();
				System.out.println();
				String confirm;
				switch (selection) {
					case "1":
						System.out.println("Create a new Space Marine named " + name + "?");
						System.out.print("(Y/N): ");
						confirm = scanner.nextLine().trim().toLowerCase();
						if (confirm.equals("y") || confirm.equals("yes")) {
							crewMember = new SpaceMarine(name);
						}
						break;
					case "2":
						System.out.println("Create a new Scout named " + name + "?");
						System.out.print("(Y/N): ");
						confirm = scanner.nextLine().trim().toLowerCase();
						if (confirm.equals("y") || confirm.equals("yes")) {
							crewMember = new Scout(name);
						}
						break;
					case "3":
						System.out.println("Create a new Mechanic named " + name + "?");
						System.out.print("(Y/N): ");
						confirm = scanner.nextLine().trim().toLowerCase();
						if (confirm.equals("y") || confirm.equals("yes")) {
							crewMember = new Mechanic(name);
						}
						break;
					case "4":
						System.out.println("Create a new Doctor named " + name + "?");
						System.out.print("(Y/N): ");
						confirm = scanner.nextLine().trim().toLowerCase();
						if (confirm.equals("y") || confirm.equals("yes")) {
							crewMember = new Doctor(name);
						}
						break;
					case "5":
						System.out.println("Create a new Chef named " + name + "?");
						System.out.print("(Y/N): ");
						confirm = scanner.nextLine().trim().toLowerCase();
						if (confirm.equals("y") || confirm.equals("yes")) {
							crewMember = new Chef(name);
						}
						break;
					case "6":
						System.out.println("Create a new Space Bard named " + name + "?");
						System.out.print("(Y/N): ");
						confirm = scanner.nextLine().trim().toLowerCase();
						if (confirm.equals("y") || confirm.equals("yes")) {
							crewMember = new SpaceBard(name);
						}
						break;
				}
				isValid = true;
			} else {
				System.out.println("Invalid option entered.\n");
			}
		}
		System.out.println();
		return crewMember;
	}

	private void displayCreateCrewHelp(Scanner scanner) {
		String selection = "";
		while (!selection.equals("7")) {
			System.out.println("Which class would you like to view?\n");
			System.out.println("1 - Space Marine");
			System.out.println("2 - Scout");
			System.out.println("3 - Mechanic");
			System.out.println("4 - Doctor");
			System.out.println("5 - Chef");
			System.out.println("6 - Space Bard");
			System.out.println("7 - Exit Help");
			System.out.print("(Selection): ");
			selection = scanner.nextLine().trim();
			System.out.println();
			switch (selection) {
				case "1":
					System.out.println("Space Marine:");
					System.out.println("A battle-hardened soldier.\n");
					System.out.println("Stats:");
					System.out.println("Max Health: 120");
					System.out.println("Max Hunger: 100");
					System.out.println("Max Tiredness: 110");
					System.out.println();
					System.out.println("Special ability:");
					System.out.println("Lowers the chance that a pirate attack will occur.");
					break;
				case "2":
					System.out.println("Scout:");
					System.out.println("An individual trained in reconnaissance.\n");
					System.out.println("Stats:");
					System.out.println("Max Health: 110");
					System.out.println("Max Hunger: 100");
					System.out.println("Max Tiredness: 120");
					System.out.println();
					System.out.println("Special ability:");
					System.out.println("Searching planets can give up to two items instead of one.");
					break;
				case "3":
					System.out.println("Mechanic:");
					System.out.println("Skilled at making repairs.\n");
					System.out.println("Stats:");
					System.out.println("Max Health: 100");
					System.out.println("Max Hunger: 100");
					System.out.println("Max Tiredness: 100");
					System.out.println();
					System.out.println("Special ability:");
					System.out.println("Repairing the ship restores twice as much health.");
					break;
				case "4":
					System.out.println("Doctor:");
					System.out.println("Saves lives.\n");
					System.out.println("Stats:");
					System.out.println("Max Health: 80");
					System.out.println("Max Hunger: 100");
					System.out.println("Max Tiredness: 100");
					System.out.println();
					System.out.println("Special ability:");
					System.out.println("Can heal allies and cure plague without the use of items.");
					break;
				case "5":
					System.out.println("Chef:");
					System.out.println("Proficient in turning ingredients into meals.\n");
					System.out.println("Stats:");
					System.out.println("Max Health: 100");
					System.out.println("Max Hunger: 120");
					System.out.println("Max Tiredness: 100");
					System.out.println();
					System.out.println("Special ability:");
					System.out.println("Can cook food to make it more effective.");
					break;
				case "6":
					System.out.println("Space Bard:");
					System.out.println("The best musician on this side of the galaxy.\n");
					System.out.println("Stats:");
					System.out.println("Max Health: 90");
					System.out.println("Max Hunger: 100");
					System.out.println("Max Tiredness: 90");
					System.out.println();
					System.out.println("Special ability:");
					System.out.println("Can perform music to lower the tiredness of other crew members.");
					break;
			}
			System.out.println();
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
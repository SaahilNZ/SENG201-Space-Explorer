package SpaceExplorer;

import java.util.ArrayList;

import SpaceExplorer.CrewMembers.CrewMember;
import SpaceExplorer.Utilities.NameGenerator;
import SpaceExplorer.Utilities.StaticData;

public class Game {
	public static final int STARTING_MONEY = 200;

	private static Game instance;
	
	private Planet currentPlanet;
	private ArrayList<Planet> planets;
	private ArrayList<Item> allItems;
	private Crew crew;
	private RandomEventController randomEventController;
	private int desiredDays;
	private int currentDay = 1;
	private int totalShipParts;
	private boolean gameWon = false;
	private int crewCount;

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
	
	public Crew getCrew() {
		return crew;
	}
	
	public ArrayList<Planet> getPlanets() {
		return planets;
	}
	
	public Planet getCurrentPlanet() {
		return this.currentPlanet;
	}
	
	public void setCurrentPlanet(Planet planet) {
		this.currentPlanet = planet;
	}
	
	public ArrayList<Item> getItems() {
		return allItems;
	}

	public int getCurrentDay() {
		return currentDay;
	}
	
	public int getTotalShipParts() {
		return totalShipParts;
	}
	
	public void startGame() {
		currentPlanet = planets.get(0);
	}
	
	public void setupGame(int days, Crew crew) {
		this.gameWon = false;
		this.desiredDays = days;
		this.crew = crew;
		this.randomEventController = new RandomEventController();
		this.totalShipParts = (this.desiredDays * 2) / 3;
		this.crewCount = crew.getCrewMembers().size();
		createItems();
		generatePlanets();
		startGame();
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
	
	private void createItems() {
		allItems = new ArrayList<Item>();
		createMedicalItems();
		createFoodItems();
	}
	
	private void createMedicalItems() {
		MedicalItem fullRestore = ItemFactory.createMedicalItem("Full Restore",
				50, false, true, 100, true);
		allItems.add(fullRestore);
		MedicalItem essentialOils = ItemFactory.createMedicalItem("Essential Oils",
				1, true, false, 0, false);
		allItems.add(essentialOils);
		MedicalItem pickMeUp = ItemFactory.createMedicalItem("Pick-Me-Up",
				10, true, true, 20, false);
		allItems.add(pickMeUp);
		MedicalItem vaccine = ItemFactory.createMedicalItem("Vaccine",
				20, false, true, 0, true);
		allItems.add(vaccine);
		MedicalItem antibiotics = ItemFactory.createMedicalItem("Antibiotics",
				30, true, true, 50, false);
		allItems.add(antibiotics);
		MedicalItem rustedSyringe = ItemFactory.createMedicalItem("Rusted Syringe",
				10, true, false, -40, true);
		allItems.add(rustedSyringe);
	}
	
	private void createFoodItems() {
		FoodItem steakBurger = ItemFactory.createFoodItem("Steak Burger",
				"Roasted Steak Burger", 25, false, true, 60, 0);
		allItems.add(steakBurger);
		FoodItem gruel = ItemFactory.createFoodItem("Gruel",
				"Chargruelled Gruel", 5, true, true, 10, 0);
		allItems.add(gruel);
		FoodItem hamAndCheese = ItemFactory.createFoodItem("Ham & Cheese Sammy",
				"Toasted Ham & Cheese Sammy", 15, false, true, 20, 0);
		allItems.add(hamAndCheese);
		FoodItem adrenalineChewy = ItemFactory.createFoodItem("Adrenaline Chewy",
				"Adrenaline Gumball", 80, true, true, -10, 100);
		allItems.add(adrenalineChewy);
		FoodItem mocha = ItemFactory.createFoodItem("Mocha",
				"Roast Bean Mocha", 30, false, true, 0, 10);
		allItems.add(mocha);
		FoodItem cigarette = ItemFactory.createFoodItem("Candy Cigarette",
				"Candy Cuban Cigar", 75, true, true, -20, 40);
		allItems.add(cigarette);
		FoodItem goldenEgg = ItemFactory.createFoodItem("Golden Egg",
				"Omelette of the Gods", 9001, true, false, 80, 100);
		allItems.add(goldenEgg);
		FoodItem beetles = ItemFactory.createFoodItem("Beetles",
				"Beetle Juice", 2, true, false, 5, -10);
		allItems.add(beetles);
		FoodItem grapes = ItemFactory.createFoodItem("Grapes",
				"Torched Grapes", 20, true, false, 15, 0);
		allItems.add(grapes);
	}

	/**
	 * Proceeds to the next day, and returns whether or not the game should continue
	 * 
	 * @return			Is the game active
	 */
	public boolean newDay() {
		this.currentDay += 1;
		randomEventController.rollRandomEvents(crew);
		boolean gameStatus = crew.newDay() > 0;
		if (gameStatus) {
			if (currentDay > desiredDays) {
				gameStatus = false;
			}
			for (Planet planet : planets) {
				Outpost outpost = planet.getOutpost();
				if (outpost != null) {
					outpost.refreshInventory();
				}
			}
		}
		return gameStatus;
	}
	
	public boolean pruneCrewMembers() {
		return crew.pruneCrewMembers() > 0;
	}
	
	public int calculateScore() {
		int score = 10000;
		for (Item item : crew.getItems()) {
			score += item.getPrice();
		}
		score -= crewCount * 500;
		score -= (2000 * (crewCount - crew.getCrewMembers().size()));
		score -= (1000 * (totalShipParts - crew.getShipPieces()));
		for (CrewMember crewMember : crew.getCrewMembers()) {
			score -= crewMember.getHunger();
			score -= crewMember.getTiredness();
			if (crewMember.hasPlague()) score -= 200;
		}
		score += crew.currentMoney();
		if (!gameWon) {
			if (score > 0) {
				score /= 2;
			} else {
				score *= 2;
			}
		}
		return score;
	}
	
	public boolean getWinStatus() {
		return gameWon;
	}
	
	public void setWinStatus(boolean status) {
		gameWon = status;
	}

	public void quitGame() {
		System.exit(0);
	}
}
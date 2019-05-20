package SpaceExplorer;

import java.util.ArrayList;

import SpaceExplorer.CrewMembers.CrewMember;
import SpaceExplorer.Utilities.NameGenerator;
import SpaceExplorer.Utilities.StaticData;

/**
 * Game class used to set up the game and keep track of the other classes used to play. Also calculates
 * player's final score and stores all the items that are possible to obtain in the game.
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 *
 */
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
	
	/**
	 * Constructs a new ArrayList of items and planets
	 */
	private Game() {
		planets = new ArrayList<Planet>();
		allItems = new ArrayList<Item>();
	}
	
	/**
	 * Used to get the instance of the current game
	 * @return					The current game instance
	 */
	public static Game getCurrentGame() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}
	
	/**
	 * Getter for the crew of this game instance
	 * @return					The crew for this game
	 */
	public Crew getCrew() {
		return crew;
	}
	
	/**
	 * Getter for the planets available to visit in the game
	 * @return					The planets available to visit
	 */
	public ArrayList<Planet> getPlanets() {
		return planets;
	}
	
	/**
	 * Getter for the current planet
	 * @return					The current planet
	 */
	public Planet getCurrentPlanet() {
		return this.currentPlanet;
	}
	
	/**
	 * Changes which planet the player is currently at
	 * @param planet			The planet that the game will move on to
	 */
	public void setCurrentPlanet(Planet planet) {
		this.currentPlanet = planet;
	}
	
	/**
	 * Getter for the current list of items available in the game
	 * @return
	 */
	public ArrayList<Item> getItems() {
		return allItems;
	}
	
	/**
	 * Getter for the current day in the game
	 * @return					The current day in game
	 */
	public int getCurrentDay() {
		return currentDay;
	}
	
	/**
	 * Getter for the total number of ship parts for the current game
	 * @return					The number of ship parts
	 */
	public int getTotalShipParts() {
		return totalShipParts;
	}
	
	/**
	 * Changes the current planet to the first planet in the list of available planets.
	 * Used when the game first begins
	 */
	public void startGame() {
		currentPlanet = planets.get(0);
	}
	
	/**
	 * Sets up the game to be played. 
	 * @param days			Number of days the player would like to play
	 * @param crew			The crew members the player would like to play with
	 */
	public void setupGame(int days, Crew crew) {
		this.gameWon = false;
		this.desiredDays = days;
		this.currentDay = 1;
		this.crew = crew;
		this.randomEventController = new RandomEventController();
		this.totalShipParts = (this.desiredDays * 2) / 3;
		this.crewCount = crew.getCrewMembers().size();
		createItems();
		generatePlanets();
		startGame();
	}
	
	/**
	 * Generates a list of random planets for the player to visit
	 */
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
	
	/**
	 * Creates a list of food and medical items available to the player
	 */
	private void createItems() {
		allItems = new ArrayList<Item>();
		createMedicalItems();
		createFoodItems();
	}
	
	/**
	 * A list of all medical items available to the player in game. To introduce more Medical items into
	 * the game, you put them into this method
	 */
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
	
	/**
	 * A list of all food items available to the player in game. To introduce more food items into
	 * the game, you put them into this method
	 */
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
		boolean gameStatus = (crew.newDay() > 0) && (crew.getShip().getHealth()>0);
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
	
	/**
	 * Calls the pruneCrewMembers method of the crew to remove dead crew members
	 * @return			False if no crew members remain in the crew
	 */
	public boolean pruneCrewMembers() {
		return crew.pruneCrewMembers() > 0;
	}
	
	/**
	 * Calculates the player's score once the game has completed
	 * @return			The player's final score
	 */
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
	
	/**
	 * Used to get whether the player has won or lost the game
	 * @return			True if the player won
	 */
	public boolean getWinStatus() {
		return gameWon;
	}
	
	/**
	 * Sets the player's win status
	 * @param status	The players win status. True if game won
	 */
	public void setWinStatus(boolean status) {
		gameWon = status;
	}
	
	/**
	 * Used to quit the game
	 */
	public void quitGame() {
		System.exit(0);
	}
}
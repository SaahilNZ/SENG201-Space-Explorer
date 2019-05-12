package SpaceExplorer;

import java.util.ArrayList;

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
	private int score;
	private boolean gameWon = false;

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
	
	public Planet getCurrentPlanet() {
		return this.currentPlanet;
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
		this.score = 0;
		this.gameWon = false;
		this.desiredDays = days;
		this.crew = crew;
		this.randomEventController = new RandomEventController();
		this.totalShipParts = (this.desiredDays * 2) / 3;
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
		// TODO: create and add items to the allItems array
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
		}
		return gameStatus;
	}
	
	public int getScore() {
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
package SpaceExplorer;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

import SpaceExplorer.CrewMembers.*;
import SpaceExplorer.GUI.StartScreen;
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
	
	public Crew getCrew() {
		return crew;
	}
	
	public ArrayList<Item> getItems() {
		return allItems;
	}

	public int getCurrentDay() {
		return currentDay;
	}
	
	public void startGame() {
		newDay();
	}
	
	public void setupGame(int days, Crew crew) {
		this.desiredDays = days;
		this.crew = crew;
		generatePlanets();
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

	private void newDay() {
		this.currentDay += 1;
	}

	public void quitGame() {
		System.exit(0);
	}
}
package SpaceExplorer;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import SpaceExplorer.CrewMembers.CrewMember;
import SpaceExplorer.CrewMembers.SpaceMarine;

/**
 * This class implements the random events that can occur 
 * when the player proceeds to a new day
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 */
public class RandomEventController {
	private static final int DAMAGE_AMOUNT = 60;
	private static final int PIRATE_CHANCE = 30;
	private static final int INFECT_CHANCE = 20;
	private static final int ASTEROID_CHANCE = 40;
	
	/**
	 * Basic constructor for a RandomEventController
	 */
	public RandomEventController() { }
	
	/**
	 * Determines what random events, if any will occur
	 * 
	 * @param crew					The player's crew
	 */
	public void rollRandomEvents(Crew crew) {
		int pirateChance = PIRATE_CHANCE;
		for (CrewMember crewMember : crew.getCrewMembers()) {
			if (crewMember instanceof SpaceMarine) {
				pirateChance -= 5;
			}
		}
		Random random = new Random();

		if (crew.getItems().size() > 0 && random.nextInt(100) < pirateChance) {
			JOptionPane.showMessageDialog(new JFrame(), stealItem(crew),
					"Alert", JOptionPane.INFORMATION_MESSAGE);
		}
		infectCrew(random, crew);
		if (random.nextInt(100) < ASTEROID_CHANCE) {
			JOptionPane.showMessageDialog(new JFrame(), damageShip(crew.getShip()),
					"Alert", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Removes a random item from the player's inventory
	 * 
	 * @param crew					The player's crew
	 * @return						A string message giving the result
	 */
	private String stealItem(Crew crew) {
		int index = (int)(Math.random() * crew.getItems().size());
		Item item = crew.getItems().remove(index);
		return ("Your " + item.getName() + " was stolen by alien space pirates!");
	}
	
	/**
	 * Randomly infects 0 or more of the player's crew members, and
	 * displays a message dialog informing the player of infected crew
	 * members
	 * 
	 * @param random				An instance of a 'Random' class
	 * @param crew					The player's crew
	 */
	private void infectCrew(Random random, Crew crew) {
		for (CrewMember crewMember : crew.getCrewMembers()) {
			if (!crewMember.hasPlague() && random.nextInt(100) < INFECT_CHANCE) {
				JOptionPane.showMessageDialog(new JFrame(), infectCrewMember(crewMember),
						"Alert", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	/**
	 * Infects the specified crew member
	 * 
	 * @param crewMember			The crew member to infect
	 * @return						A string message giving the result
	 */
	private String infectCrewMember(CrewMember crewMember) {
		crewMember.setPlague(true);
		return (crewMember.getName() + " has contracted the space plague.");	
	}
	
	/**
	 * Damages the player's ship
	 * 
	 * @param ship					The player's ship
	 * @return						A string message giving the result
	 */
	private String damageShip(Ship ship) {
		int shipDamage = DAMAGE_AMOUNT - (ship.getShieldLevel() / 10);
		ship.damageShip(shipDamage);
		boolean shieldsDamaged = false;
		int shieldDamageAmount = 0;
		if (ship.getShieldLevel() > 0) {
			shieldDamageAmount = ship.getShieldLevel() - ship.damageShield(DAMAGE_AMOUNT);
			shieldsDamaged = true;
		}
		return ("The " + ship.getName() + " has travelled through an asteroid field and has taken " + shipDamage + " points of damage."
				+ (shieldsDamaged ? " Additionally, shield levels have been decreased by " + shieldDamageAmount + " points." : ""));
	}
}

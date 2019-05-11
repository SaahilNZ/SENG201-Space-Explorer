package SpaceExplorer;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import SpaceExplorer.CrewMembers.CrewMember;
import SpaceExplorer.CrewMembers.SpaceMarine;

public class RandomEventController {
	private static final int DAMAGE_AMOUNT = 20;
	private static final int PIRATE_CHANCE = 30;
	private static final int INFECT_CHANCE = 20;
	private static final int ASTEROID_CHANCE = 20;
	
	public RandomEventController() { }
	
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
	
	private String stealItem(Crew crew) {
		int index = (int)(Math.random() * crew.getItems().size());
		Item item = crew.getItems().remove(index);
		return ("Your " + item.getName() + " was stolen by alien space pirates!");
	}
	
	private void infectCrew(Random random, Crew crew) {
		for (CrewMember crewMember : crew.getCrewMembers()) {
			if (!crewMember.hasPlague() && random.nextInt(100) < INFECT_CHANCE) {
				JOptionPane.showMessageDialog(new JFrame(), infectCrewMember(crewMember),
						"Alert", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	private String infectCrewMember(CrewMember crewMember) {
		crewMember.setPlague(true);
		return (crewMember.getName() + " has contracted the space plague.");	
	}
	
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

package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SpaceExplorer.Ship;
import SpaceExplorer.CrewMembers.Mechanic;

/**
 * This class tests the functionality of the Mechanic sub class
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 *
 */
class MechanicTest {
	
	private Mechanic testMech;
	private Ship canterbury;
	
	/**
	 * Initializes a mechanic and ship for testing
	 */
	@BeforeEach
	public void init() {
		testMech = new Mechanic("Claptrap");
		canterbury = new Ship("Canterbury", 200, 200);
		
	}
	
	/**
	 * Tests a ship can be repaired by the mechanic
	 */
	@Test
	public void repairTest() {
		canterbury.damageShield(100);
		canterbury.damageShip(100);
		testMech.repairShip(canterbury);
		assertEquals(160, canterbury.getShieldLevel());
		assertEquals(140, canterbury.getHealth());
	}
	
	/**
	 * Tests a mechanic with insufficient actions cannot repair a ship
	 */
	@Test
	public void depletedActionsTest() {
		testMech.takeAction();
		testMech.takeAction();
		canterbury.damageShield(100);
		canterbury.damageShip(100);
		testMech.repairShip(canterbury);
		assertEquals(100, canterbury.getShieldLevel());
		assertEquals(100, canterbury.getHealth());
	}

}

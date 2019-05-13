package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SpaceExplorer.Ship;
import SpaceExplorer.CrewMembers.Mechanic;

class MechanicTest {
	
	private Mechanic testMech;
	private Ship canterbury;

	@BeforeEach
	public void init() {
		testMech = new Mechanic("Claptrap");
		canterbury = new Ship("Canterbury", 200, 200);
		
	}
	
	@Test
	public void repaiTest() {
		canterbury.damageShield(100);
		canterbury.damageShip(100);
		testMech.repairShip(canterbury);
		assertEquals(160, canterbury.getShieldLevel());
		assertEquals(140, canterbury.getHealth());
	}

}

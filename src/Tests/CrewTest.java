package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SpaceExplorer.Crew;
import SpaceExplorer.FoodItem;
import SpaceExplorer.CrewMembers.CrewMember;
import SpaceExplorer.CrewMembers.Scout;
import SpaceExplorer.Ship;
import SpaceExplorer.Item;

class CrewTest {
	
	private Scout testDummy1;
	private Scout testDummy2;
	private Scout testDummy3;
	private Ship dinglebud;
	public ArrayList<CrewMember> testCrew;
	public ArrayList<Item> items;
	public Crew canterbury;
	
	@BeforeEach
	public void init() {
		testDummy1 = new Scout("Claptrap");
		testDummy2 = new Scout("Michael Scott");
		testDummy3 = new Scout("Izaro");
		dinglebud = new Ship("Dinglebud", 200, 200);
		testCrew = new ArrayList<CrewMember>();
		testCrew.add(testDummy1);
		testCrew.add(testDummy2);
		testCrew.add(testDummy3);
		canterbury = new Crew(testCrew, "Canterbury", dinglebud, 0, items);
	}
	
	@Test
	public void createCrew() {
		assertEquals(testCrew, canterbury.getCrewMembers());
	}
	
	@Test
	public void memberRemoval() {
		canterbury.removeMember(testDummy1);
		testCrew.remove(testDummy1);
		assertEquals(testCrew, canterbury.getCrewMembers());
	}
	
	@Test
	public void moneyTester() {
		canterbury.receiveMoney(200);
		assertEquals(200, canterbury.currentMoney());
		
		//Tests removing money
		canterbury.deductMoney(100);
		assertEquals(100, canterbury.currentMoney());
		
		//Tests money extremes
		canterbury.receiveMoney(500000900);
		assertEquals(500001000, canterbury.currentMoney());
		canterbury.deductMoney(600000000);
		assertEquals(0, canterbury.currentMoney());
	}
	
	@Test
	public void crewDeathTest() {
		testDummy1.damageCrew(200);
		canterbury.pruneCrewMembers();
		assertFalse(canterbury.getCrewMembers().contains(testDummy1));
	}

	@Test
	public void newDayTest() {
		int startHealth = testDummy1.getHealth();
		testDummy1.setPlague(true);
		testDummy1.increaseHunger(200);
		testDummy1.becomeTired(200);
		testDummy2.damageCrew(200);
		canterbury.newDay();
		assertEquals(startHealth - 40, testDummy1.getHealth());
		assertEquals(1, testDummy1.getActions());
		assertFalse(canterbury.getCrewMembers().contains(testDummy2));
	}
	
	@Test
	public void loadItems() {
		FoodItem jello = new FoodItem(1,"Jello", "Mint Jello", 200, true, true, 10, 10);
		items = new ArrayList<Item>();
		items.add(jello);
		Crew enterprise = new Crew(testCrew, "Enterprise", dinglebud, 0, items);
		assertTrue(enterprise.getItems().contains(jello));
		
		enterprise.removeItem(jello);
		assertFalse(enterprise.getItems().contains(jello));
		
		enterprise.addItem(jello);
		assertTrue(enterprise.getItems().contains(jello));
	}
	
}

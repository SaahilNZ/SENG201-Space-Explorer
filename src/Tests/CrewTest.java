package Tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SpaceExplorer.Crew;
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

}

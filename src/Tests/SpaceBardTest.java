package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SpaceExplorer.Crew;
import SpaceExplorer.Item;
import SpaceExplorer.Ship;
import SpaceExplorer.CrewMembers.CrewMember;
import SpaceExplorer.CrewMembers.Doctor;
import SpaceExplorer.CrewMembers.Scout;
import SpaceExplorer.CrewMembers.SpaceBard;

class SpaceBardTest {
	
	private SpaceBard testBard;
	private Doctor testCrew1;
	private Scout testCrew2;
	private Ship dinglebud;
	public ArrayList<CrewMember> testCrew;
	public ArrayList<Item> items;
	public Crew canterbury;
	
	@BeforeEach
	public void init() {
		testBard = new SpaceBard("Dandelion");
		testCrew1 = new Doctor("Edward Scissor Hands");
		testCrew2 = new Scout("Claptrap");
		dinglebud = new Ship("Dinglebud", 200, 200);
		testCrew = new ArrayList<CrewMember>();
		testCrew.add(testCrew1);
		testCrew.add(testCrew2);
		testCrew.add(testBard);
		canterbury = new Crew(testCrew, "Canterbury", dinglebud, 0, items);
	}
	
	@Test
	public void testPerform() {
		testBard.modifyTiredness(30);
		testCrew1.modifyTiredness(30);
		testCrew2.modifyTiredness(30);
		testBard.performMusic(canterbury.getCrewMembers());
		assertEquals(10, testCrew1.getTiredness());
		assertEquals(10, testCrew2.getTiredness());
		assertEquals(50, testBard.getTiredness());
	}

	@Test
	public void depletedActionsTest() {
		testBard.takeAction();
		testBard.takeAction();
		testCrew1.modifyTiredness(30);
		testBard.performMusic(canterbury.getCrewMembers());
		assertEquals(30, testCrew1.getTiredness());
	}
}

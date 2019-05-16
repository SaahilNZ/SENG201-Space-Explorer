package SpaceExplorer.CrewMembers;

import SpaceExplorer.Crew;
import SpaceExplorer.Planet;

public class Scout extends CrewMember {
	public Scout(String name) {
		super(name, "Scout", 110, DEFAULT_HUNGER, 120);
	}
	
	@Override
	public ActionResult searchPlanet(Crew crew, Planet planet) {
		String message = "";
		boolean success = false;
		if (takeAction()) {
			message += getName() + " searches " + planet.toString() + " twice:\n";
			message += searchPlanetOnce(crew, planet);
			message += searchPlanetOnce(crew, planet);
		} else {
			message = getName() + " doesn't have enough actions left to search the planet.";
		}
		
		return new ActionResult(message, success);
	}
}

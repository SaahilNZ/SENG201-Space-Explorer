package SpaceExplorer.CrewMembers;

import SpaceExplorer.Crew;
import SpaceExplorer.Planet;

/**
 * This class implements a Scout. Modifies the searchPlanet method of the parent class to be more effective.
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 *
 */
public class Scout extends CrewMember {
	
	/**
	 * Default crew member constructor
	 * 
	 * @param name			Name of the scout
	 */
	public Scout(String name) {
		super(name, "Scout", 110, DEFAULT_HUNGER, 120);
	}
	
	/**
	 * Takes a crew and planet and allows the scout to search it twice if they have enough actions
	 * 
	 * @param crew			The crew
	 * @param planet		The planet to be searched
	 * @return				A message based on whether the action was successful or not
	 */
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

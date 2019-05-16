package SpaceExplorer.CrewMembers;

import java.util.Collection;

/**
 * This class implements a SpaceBard and its associated functionality
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 *
 */
public class SpaceBard extends CrewMember {
	
	/**
	 * Uses default constructor method of the parent class
	 * 
	 * @param name			Name of the spacebard
	 */
	public SpaceBard(String name) {
		super(name, "Space Bard", 90, DEFAULT_HUNGER, 90);
	}
	
	/**
	 * Takes a list of the crew members and reduces the tiredness of all crew members that aren't the current spacebard
	 * 
	 * @param crewMembers	An collection of crew members
	 * @return				A message based on whether the action was successful or not
	 */
	public ActionResult performMusic(Collection<CrewMember> crewMembers) {
		String message = "";
		boolean success = false;
		if (takeAction()) {
			for (CrewMember crewMember : crewMembers) {
				if (crewMember != this) {
					crewMember.decreaseTiredness(20);
				}
			}
			message += getName() + " performs music for the crew, decreasing the crew's tiredness by 20 points.";
			success = true;
		} else {
			message += getName() + " does not have enough actions left to perform.";
		}
		return new ActionResult(message, success);
	}
	
}

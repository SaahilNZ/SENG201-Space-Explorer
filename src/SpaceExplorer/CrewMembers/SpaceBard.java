package SpaceExplorer.CrewMembers;

import java.util.Collection;

public class SpaceBard extends CrewMember {
	public SpaceBard(String name) {
		super(name, "Space Bard", 90, DEFAULT_HUNGER, 90);
	}
	
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

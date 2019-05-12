package SpaceExplorer.CrewMembers;

import java.util.Collection;

public class SpaceBard extends CrewMember {
	public SpaceBard(String name) {
		super(name, "Space Bard", 90, DEFAULT_HUNGER, 90);
	}
	
	public void performMusic(Collection<CrewMember> crewMembers) {
		if (getActions() > 0) {
			for (CrewMember crewMember : crewMembers) {
				if (crewMember != this) {
					crewMember.decreaseTiredness(20);
				}
			}
			takeAction();
		}
	}
	
}

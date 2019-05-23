package SpaceExplorer.CrewMembers;

/**
 * This class implements a Doctor crew member. Along with all other crew member functionality, this class
 * can also heal and cure plague for other crew members 
 * 
 * @author Saahil Hari and Isaac Walton
 * @version 1.0, May 2019
 *
 */
public class Doctor extends CrewMember {
	
	/**
	 * Default crew member constructor
	 * 
	 * @param name			Name of the doctor
	 */
	public Doctor(String name) {
		super(name, "Doctor", 80, DEFAULT_HUNGER, DEFAULT_TIREDNESS);
    }
	
	/**
	 * Takes a crew member and allows the doctor to remove the plague status from it
	 * 
	 * @param member			A member of the crew
	 * @return					A message based on whether the actions was successful or not
	 */
	public ActionResult curePlague(CrewMember member) {
		String message = "";
		boolean success = false;
		if (getActions() >= 2) {
			message += getName() + " has successfully cured " + member.getName() + " of the space plague.";
			member.setPlague(false);
			takeAction();
			takeAction();
			success = true;
		} else {
			message += getName() + " does not have enough actions to cure someone of space plague.";
		}
		return new ActionResult(message, success);
	}
	
	/**
	 * Takes a crew member and allows the doctor to heal it
	 * 
	 * @param member			A member of the crew
	 * @return					A message based on whether the actions was successful or not
	 */
	public ActionResult heal(CrewMember member) {
		String message = "";
		boolean success = false;
		if (takeAction()) {
			message += getName() + " has successfully healed " + member.getName() + " for 20 health points.";
			member.modifyHealth(20);
			success = true;
		} else {
			message += getName() + " does not have enough actions to heal someone";
		}
		return new ActionResult(message, success);
	}
	
}


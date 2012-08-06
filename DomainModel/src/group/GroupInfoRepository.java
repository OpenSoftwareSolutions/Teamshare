package group;

import java.util.List;

import change.Change;
import common.Group;

/**
 * Implements the @{link group}'s module repository component, which offers an interface for accessing and modifying group information. 
 * The group information, the one that is contained in the Group objects, 
 * and from which these objects are recreated, is stored and modified using this class. 
 * The way this information is stored is implementation specific.
 *
 */
public abstract class GroupInfoRepository {
	
	/**
	 * Modifies the information stored for the given group, based on the Change object.
	 * @param group - the group for which to apply the change
	 * @param change - the group change
	 */
	public abstract void applyGroupChange(Group group, Change change);
	
	/**
	 * Returns a list of Group objects representing the groups the logged-in user is member of.
	 * @return the user's groups.
	 */
	public abstract List<Group> getGroups();
}

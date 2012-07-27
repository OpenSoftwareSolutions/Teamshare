package group;

import java.util.List;

import change.Change;
import common.Group;

/**
 * Implements the GroupInfoRepository that offers an interface for accessing and modifying groups information. The group information,
 * the one that is contained in the Group objects, and from which these objects are recreated, is stored and modified using this class. The
 * way this information is stored is implementation specific.
  *
 */
public abstract class GroupInfoRepository {

	public abstract void applyGroupChange(Group group, Change change);
	public abstract List<Group> getGroups();
	
}

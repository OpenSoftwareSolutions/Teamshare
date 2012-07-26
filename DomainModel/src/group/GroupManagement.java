package group;

import common.Group;
import common.User;

public abstract class GroupManagement {
	
	public abstract void addUser(Group group, User user);
	public abstract void removeUser(Group group, User user);
	
	
}

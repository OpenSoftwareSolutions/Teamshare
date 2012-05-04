package common;

import groupmanagement.Group;

import java.util.List;

/**
 * Class that represents a User.
 * @author adriana
 *
 */
public class User {

	private long id;
	private String email;
	/**
	 * The list of groups the user is member of.
	 */
	private List<Group> groups;
	
	public List<Group> getGroups() {
		return groups;
	}
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	/**
	 * Adds a new group to the list of groups the user is member of.
	 * @param group
	 */
	public void addGroup(Group group){
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
}

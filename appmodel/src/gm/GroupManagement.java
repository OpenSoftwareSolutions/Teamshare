/**
 * 
 */
package gm;

import java.util.List;

import basic.User;

/**
 * 
 * The interface of the Group Management Component with the rest of the services 
 * (the entry-point of this component)
 * @author adriana
 *
 */
public class GroupManagement {

	private List<Group> groups;
	private MessageManager messageManager; 
	
	
	public void transferOwnership(Group g, User newOwner){
		
	}
	public User getOwner(Group g){
		return null;
	}
	
	public void informChange(Group g){
		
	}
	public void leave(Group g) {
		
	}
	public void kickout(Group g, User user) {
		
	}
	public void invite(Group g, String userEmail) {
		
	}
	
	/** Invoked when creating a group. The GroupInfo contains basic info like filename
	 *  and the group management service fills/creates the other info, and stores the group information
	 *  in a file 
	 */
	public void createGroup(Group g) {		
	
	}
	
	/** Invoked when removing a group */
	public void cleanupGroup(Group g) {
		
	}
	
	public void receivedMessage(GroupMessage g) {
		
		
	}
	
	
	
}

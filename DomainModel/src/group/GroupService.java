/**
 * 
 */
package group;

import messaging.Message;

import change.Change;
import common.Group;
import common.User;

/**
 * Interface offering other components group related functionalities.
 *
 */
public interface GroupService {
	

	/**
	 * Group Service starts performing the necessary steps for inviting an user into the given group. 
	 * @param user - the invited user
	 * @param group - the group to which the user is invited
	 */
	public void inviteUser(User user, Group group);
	
	/**
	 * Group Service starts performing the necessary steps for inviting an external person into the given group. 
	 * @param email - the external person's email address, to which the invitation is sent
	 * @param group - the group to which the user is invited
	 */
	public void inviteExternalPerson(String email, Group group);
	
	/**
	 * Group Service starts performing the necessary steps for ownership transfer.
	 * @param newOwner - the proposed group owner
	 * @param group - the group for which the ownership is transferred
	 */
	public void changeOwner(User newOwner, Group group);
	/**
	 * The {@link SynchronizationService} component informs the Group Service component about a group change, and the Group Service in turn will transmit this 
	 * information for processing to the {@link GroupManagement} component.
	 * @param change - the Change object, also containing the Group to which it applies and what was performed that lead to it.
	 */
	public void informGroupChange(Change change);
	
	/**
	 * The {@link MessagingService} forwards a read message to the Group Service in order to apply group changes depending on the message content and type.
	 * E.g if the message is an invitation acceptance, the {@link GroupManagement} will add the user to the group and then use {@link SynchronizationService}
	 * to transmit this change to other users.
	 * 
	 * @param message 
	 */
	public void processMessage(Message message); 
}

/**
 * 
 */
package group;

import java.util.ArrayList;

import messaging.Message;

import change.Change;

import common.Device;
import common.Group;
import common.User;

/**
 * Interface offering other components group related functionalities.
 *
 */
public interface GroupService {
	

	/**
	 * Group Service starts performing the necessary steps for inviting an user into the given group. 
	 * @param user
	 * @param group
	 */
	public void inviteUser(User user, Group group);
	
	/**
	 * Group Service starts performing the necessary steps for inviting an external person into the given group. 
	 * @param user
	 * @param group
	 */
	public void inviteExternalPerson(String email, Group group);
	
	/**
	 * Group Service starts performing the necessary steps for ownership transfer. 
	 * @param user
	 * @param group
	 */
	public void changeOwner(User newOwner, Group group);
	/**
	 * The {@link SynchronizationService} component informs the Group Service component about a group change, and the Group Service in turn will transmit this 
	 * information for processing to the {@link GroupManagement} component.
	 * @param change
	 * @param group
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

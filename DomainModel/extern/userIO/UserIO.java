package userIO;

import change.ChangeService;
import group.GroupService;
import topology.TopologyService;
import network.NetworkService;
import messaging.MessagingService;

/**
 * This component represents the user interface, which receives input from the user as well as retrieving
 * information for visualization.
 *
 */
public abstract class UserIO {
	/**
	 * The user interface uses the {@link MessagingService} to get the list (or a partial one) of messages 
	 * in the user's <b>Mailbox</b>. 
	 */
	MessagingService messagingService;
	/**
	 * The user interface uses the {@link TopologyService} to get information about the <b>user's devices</b>.
	 */
	TopologyService topologyService;
	/**
	 * The user interface uses the {@link GroupService} to get information about the user's groups or to
	 * inform the GroupService about group changes performed by the user (e.g. changed group's settings, 
	 * removed a user etc). It also communicates with the group service when the user wants to send or 
	 * reply to a message. The {@link GroupService} is the one that creates the message and in turn forwards it 
	 * to the {@link MessagingService}.
	 */
	
	GroupService groupService;
	
	/**
	 * The user interface uses the {@link ChangeService} to get the list of changes, so that the user
	 * can view the <i>history of changes</i>
	 */	
	ChangeService changeService;
	
	//NetworkService networkService;
}

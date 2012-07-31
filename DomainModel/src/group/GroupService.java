package group;

import java.util.Properties;

import security.SecurityService;
import synchronization.SynchronizationService;
import topology.TopologyService;

import messaging.Message;
import messaging.MessageFactory;
import messaging.MessagingService;

import change.Change;
import change.ChangeFactory;
import change.ChangeService;
import common.Group;
import common.User;

/**
 * Interface offering group related functionalities other components.
 *
 */
public abstract class GroupService {

	GroupInfoRepository groupInfoRepository;

	/**
	 * A reference to the ChangeFactory that creates Change objects.
	 */
	ChangeFactory changeFactory;

	/**
	 * A reference to the ChangeService that logs group changes.
	 */
	ChangeService changeService;

	/**
	 * A reference to the SynchronizationService that will log group changes.
	 */
	SynchronizationService syncService;

	/**
	 * The GroupService uses the MessagingService for sending messages to other users.
	 */
	MessagingService messagingService;

	/**
	 * A reference to the SecurityService, used by the GroupService to obtain secure tokens.
	 */
	SecurityService securityService;

	/**
	 * The GroupService needs to inform the TopologyService when group users changes occur, such
	 * as a new user joined the group, or a user left the group.
	 */
	TopologyService topologyService;

	/**
	 * The GroupService uses the MessageFactory for creating Message objects for
	 * messages that it needs to send to other users.
	 */
	MessageFactory messageFactory;

	/**
	 * GroupService starts performing the necessary steps for inviting an user into the given group.
	 * @param group - the group to which the user is invited
	 * @param user - the invited user
	 */
	public abstract void inviteUser(Group group, User user);

	/**
	 * GroupService starts performing the necessary steps for inviting an external person into the given group.
	 * @param group - the group to which the user is invited
	 * @param email - the external person's email address, to which the invitation is sent
	 */
	public abstract void inviteExternalPerson(Group group, String email);

	/**
	 * GroupService starts performing the necessary steps for removing an user from the given group.
	 * @param group - the group from which the user is removed
	 * @param user - the user to be removed
	 */
	public abstract void removeUser(Group group, User user);

	/**
	 * GroupService applies the new group settings.
	 * @param group
	 * @param settings
	 */
	public abstract void changeGroupSettings(Group group, Properties settings);

	/**
	 * GroupService starts performing the necessary steps for ownership transfer.
	 * @param group - the group for which the ownership is transferred
	 * @param newOwner - the proposed group owner
	 */
	public abstract void changeOwner(Group group, User newOwner);

	/**
	 * The GroupService completely removes a group from the system. It also removes the group's file and folders, in which case it has to communicate with
	 * the {@link filesystem} module.
	 * @param group - the group to be removed/deleted, it must not have any users, just the owner, which the logged in user
	 */
	public abstract void removeGroup(Group group);

	/**
	 * The {@link SynchronizationService} component informs the GroupService component about a group change.
         * The GroupService will process the change and will transmit this information for storing to the
	 * {@link GroupInfoRepository} component.
	 * @param change - the Change object, also containing the Group to which it applies and what was performed that lead to it
	 */
	public abstract void receiveGroupChange(Change change);


	/**
	 * The {@link MessagingService} forwards a read message to the GroupService in order to apply group changes depending on the message content and type.
	 * E.g. if the message is an invitation acceptance, the GroupService will add the user to the group and then use {@link SynchronizationService}
	 * to transmit this change to other users.
	 *
	 * @param message
	 */
	public abstract void processMessage(Message message);

	/**
	 * The GroupService informs the {@link ChangeService} about a <b>group change</b> that it needs to log.
	 * @param change - the change that occurred for a group
	 */
	protected abstract void logGroupChange(Change change);

	/**
	 * The GroupService informs the {@link SynchronizationService} about a <b>group change</b> that it needs to synchronize.
	 * @param change - the change that occurred for a group
	 */
	protected abstract void syncGroupChange(Change change);

	/**
	 * The GroupService uses the ChangeFactory to obtain a Change object for a group.
	 */
	protected abstract void getGroupChange();
}

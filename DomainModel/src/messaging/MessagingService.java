package messaging;

import java.util.List;

import userIO.UserIO;

import network.NetworkService;
import group.GroupService;

public abstract class MessagingService {

	/**
	 * Messages are stored and retrieved via the Mailbox repository component.
	 */
	Mailbox mailbox;

	/**
	 * As messages imply group changes, the MessagingService must also communicate with the GroupService.
	 */
	GroupService groupService;

	/**
	 * All messages are sent and received via the NetworkService, which is the one that contact/is contacted by
	 * the remote services.
	 */
	NetworkService networkService;

	/**
	 * The user must be notified about new messages.
	 */
	UserIO userIO;

	/**
	 * The MessagingService receives a new Message from the remote centralized Messaging service.
	 * @param message - the message (invitation, ownership transfer, reply)
	 */
	public abstract void receiveMessage(Message message);

	/**
	 * Sends a message to another user and also stores it in the Mailbox.
	 * The message will be sent via the NetworkService
	 * to the remote Messaging Service, which will forward it to the destination user.
	 * @param message - the message (invitation, ownership transfer, reply)
	 */
	public abstract void sendMessage(Message message);

	/**
	 * Informs the {@link GroupService} about a message it has to process.
	 * @param message
	 */
	protected abstract void informMessage(Message message);

	/**
	 * The MessagingService contacts the remote centralized Messaging service to
	 * retrieve any new messages. The message will still be kept on the remote service
	 *  after this retrieval. This method returns nothing, the NetworkingService will
	 *  inform the MessagingService when it receives the reply from the remote service.
	 */
	protected abstract void checkForMessages();

	/**
	 * A wrapper over the method offered by {@link Mailbox}, it provides to other components a list of all the messages in the user's
	 * mailbox.
	  * @return a list of all the user's messages
	 */
	public abstract List<Message> getAllMessages();

	/**
	 * A wrapper over the method offered by {@link Mailbox}, it provides a subset of messages of the
	 * logged in user based on the given criteria.
	 * @param criteria - filtering constraints for the list of messages (certain interval, certain destination etc)
	 * @return a list of Message objects
	 */
	public abstract List<Message> getMessages(MessageQueryCriteria criteria);
}

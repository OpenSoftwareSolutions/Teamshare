package messaging;

import java.util.List;

/**
 * Implements a user's mailbox, a repository-type element, 
 * providing the set of Message objects of sent or received by an <b>user</b>. The Mailbox is linked
 * to the logged-in user's account. 
 *
 */
public abstract class Mailbox {

	
	/**
	 * Adds newly received messages to the mailbox. 
	 */
	public abstract void addMessages(List<Message> messages);
	/**
	 * Removes(deletes) a message from the mailbox.
	 */
	public abstract void removeMessage(Message message);
	/**
	 * Returns a list of all messages of the logged in user
	 * @return a list of Message objects
	 */
	public abstract List<Message> getAllMessages();
	
	/**
	 * Returns a subset of messages of the logged in user based on the given criteria.
	 * @param criteria - filtering constraints for the list of messages (certain interval, certain destination etc)
	 * @return a list of Message objects
	 */
	public abstract List<Message> getMessages(MessageQueryCriteria criteria);
	
	/**
	 * Updates the information stored for the message (it might have been change locally or on
	 * another device)
	 * @param message
	 */
	public abstract void updateMessage(Message message);

	
}

package com.oss.teamshare.messaging;

import java.util.Collection;

/**
 * Represents a user's mailbox, a repository-type element, providing the set of
 * Message objects sent or received by an <b>user</b>. It's an interface that
 * hides the actual storage of objects. It's not responsible for the retrieval
 * of messages.
 */

public interface Mailbox {

  /**
   * Returns a list of messages of the logged-in user based on the given query
   * criteria.
   * 
   * @param query
   *          - filtering constraints for the list of messages (certain
   *          interval, certain destination etc)
   * @return a list of Message objects
   */
  public Collection<Message> getMessages(MessageQuery query);

  /**
   * Adds a new message to the mailbox.
   */
  public void addMessage(Message msg);

  /**
   * Adds newly received or sent messages to the mailbox.
   */
  public void addMessages(Collection<Message> messages);

  /**
   * Removes (deletes) a message from the mailbox.
   * 
   * @param msg
   *          - the message to be deleted
   */
  public void removeMessage(Message msg);

  /**
   * Updates the message's status (read/unread), see also {@link MessageStatus}
   * 
   * @param msg
   *          - the message whose status to updated
   * @param newStatus
   *          - the message's new status
   */
  public void updateMessageStatus(Message msg, MessageStatus newStatus);

  /**
   * Triggers a check for new message and updates on existing message. The
   * actual retrieval(pull) of messages must be done in a different component,
   * such as {@link MessagingService}.
   * 
   */
  public void checkForMessages();
}

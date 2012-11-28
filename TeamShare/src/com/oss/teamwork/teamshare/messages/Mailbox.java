package com.oss.teamwork.teamshare.messages;

import java.util.List;

/**
 * Implements a user's mailbox, a repository-type element,  providing the set of 
 * Message objects sent or received by an <b>user</b>. 
 */

public interface Mailbox {

  /**
   * Returns a list of messages of the logged-in user based on the given query criteria.
   * @param query - filtering constraints for the list of messages (certain interval, certain destination etc)
   * @return a list of Message objects
   */
  public List<Message> getMessages(MessageQuery query);
  
  /**
   * Adds a new message to the mailbox.
   */  
  public void addMessage(Message msg);
  
  /**
   * Adds newly received or sent messages to the mailbox.
   */
  public void addMessages(List<Message> messages);
  /**
   * Removes (deletes) a message from the mailbox.
   * @param msg - the message to be deleted
   */
  public void removeMessage(Message msg);
  /**
   * Updates the message's status (read/unread), see also {@link MessageStatus}
   * @param msg - the message whose status to updated
   * @param newStatus - the message's new status
   */
  public void updateMessageStatus(Message msg, MessageStatus newStatus);
 
}

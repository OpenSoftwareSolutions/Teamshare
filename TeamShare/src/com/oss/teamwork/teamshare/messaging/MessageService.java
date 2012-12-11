package com.oss.teamwork.teamshare.messaging;

import com.oss.teamwork.teamshare.group.Group;
import com.oss.teamwork.teamshare.group.User;

public abstract class MessageService {

  protected Mailbox mailbox;

  /*  ______________________________________________________________________
   * | OPERATIONS                                                           |
   * |______________________________________________________________________|
  */
  
  /**
   * The MessagingService contacts the remote centralized Messaging service to
   * retrieve any new messages. The message will still be kept on the remote
   * service after this retrieval. This method returns nothing, the
   * NetworkingService will inform the MessagingService when it receives the
   * reply from the remote service.
   */
  public abstract void pull();

  /**
   * Sends an invitation message to another user and also stores it in the
   * Mailbox. The message will be sent to the remote Messaging Service, which
   * will store it in the destination user's mailbox.
   * 
   * @param invitedUser
   *          - the invited user
   * @param content
   *          - the String containing the invitation's text message written by
   *          the user for the invited user
   */
  public abstract void sendInvitation(User invitedUser, 
      String content, 
      Group group);

  /**
   * Sends an invitation message to an external person and also stores it in the
   * Mailbox. The message will be sent to the remote Messaging Service, which
   * will send an invitation email message to the external person.
   * 
   * @param externalPersonEmail
   *          - the external person's email address
   * @param content
   *          - the String containing the invitation's text message written by
   *          the user for the invited person
   */
  public abstract void sendInvitation(String externalPersonEmail,
      String content,
      Group group);

  public abstract void sendOwnershipTransfer(User newOwner,
      String content);

  public abstract void sendReply(Message replyto);
  
  public abstract Mailbox getMailbox();

  /**
   * Used by the MessagingService to forward a message to the remote Messaging
   * Service.
   * 
   * @param message
   */
  protected abstract void sendMessage(Message message);

  

  protected abstract void informGroupService();
  
  

}

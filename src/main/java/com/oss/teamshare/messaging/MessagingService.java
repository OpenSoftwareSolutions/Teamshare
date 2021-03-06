package com.oss.teamshare.messaging;

public abstract class MessagingService {

  protected Mailbox mailbox;

  /**
   * The MessagingService contacts the remote centralized Messaging service to
   * retrieve any new messages. The message will still be kept on the remote
   * service after this retrieval. This method returns nothing, the
   * NetworkingService will inform the MessagingService when it receives the
   * reply from the remote service.
   */
  public abstract void pull();

  // TODO o metoda pt cazul in care noi cerem mailbox intr-un query mesaje mai
  // vechi, care nu le avem descarcate pe acest device, si atunci trebuie aduse
  // de pe remote service  
  
   
  /*
   * DEPRECATED STUFF /** Sends an invitation message to another user and also
   * stores it in the Mailbox. The message will be sent to the remote Messaging
   * Service, which will store it in the destination user's mailbox.
   * 
   * public abstract void sendInvitation(InvitationMessage msg);
   * 
   * public abstract void replyToInvitation(InvitationMessage msg);
   * 
   * public abstract void sendOwnershipTransfer(OwnershipTransferMessage msg);
   * 
   * public abstract void replyToOwnershipTransfer(OwnershipTransferMessage
   * msg);
   */

  /**
   * Sends a message to another user and also stores it in the Mailbox. The
   * message will be sent to the remote Messaging Service, which will store it
   * in the destination user's mailbox.
   */
  public abstract void send(Message msg);

}

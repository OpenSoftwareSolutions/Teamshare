package com.oss.teamwork.teamshare.messages;

/**
 * Factory component that creates different types of Message objects depending on their purpose 
 * (invitations, ownership transfers, replies). *
 */
public interface MessageFactory {
  
  public Message createInvitationMessage();
  
  public Message createOwnerTransferMessage();
  
  public Message createReply(Message replyto);
}

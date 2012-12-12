package com.oss.teamwork.teamshare.messaging;

/**
 * Factory component that creates different types of Message objects depending
 * on their purpose (invitations, ownership transfers, replies). *
 */
public class MessageFactory {

  public static Message createInvitationMessage() {
    // TODO createInvitationMessage
    return null;
  }

  public static Message createOwnerTransferMessage() {
    // TODO createOwnerTransferMessage
    return null;
  }

  public static Message createReply(Message replyto) {
    // TODO createReply (is it required?)
    return null;
  }
}

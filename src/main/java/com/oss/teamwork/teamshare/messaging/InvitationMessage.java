package com.oss.teamwork.teamshare.messaging;

import com.oss.teamwork.teamshare.team.Team;

public class InvitationMessage extends Message implements AcceptableMessage,
    ActionMessage {

  AcceptableMessage.Type type = AcceptableMessage.Type.REQUEST;
  Team group; // MessagingService will obtain a reference from Session.

  @Override
  public void accept() {
    // TODO
    type = AcceptableMessage.Type.ACCEPT;
  }

  @Override
  public void refuse() {
    // TODO
    type = AcceptableMessage.Type.REFUSE;
  }

  @Override
  public void executeAction() {
    if (type == AcceptableMessage.Type.ACCEPT) {
      group.acceptInvitation(source);
    }
    else  if (type == AcceptableMessage.Type.REFUSE) {
      group.refuseInvitation(source);
    }
    else {
      throw new UnsupportedOperationException("Cannot execute action for request messages.");
    }
  }

}

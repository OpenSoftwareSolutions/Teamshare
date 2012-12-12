package com.oss.teamwork.teamshare.messaging;

import com.oss.teamwork.teamshare.group.Group;

public class InvitationMessage extends Message implements AcceptableMessage {

  AcceptableMessage.Type type = AcceptableMessage.Type.REQUEST;
  Group group;
  
  @Override
  public void send() {
    // TODO
  }

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

}

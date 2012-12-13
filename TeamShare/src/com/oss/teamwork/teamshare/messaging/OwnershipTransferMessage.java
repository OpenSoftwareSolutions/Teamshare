package com.oss.teamwork.teamshare.messaging;

import com.oss.teamwork.teamshare.group.User;

public class OwnershipTransferMessage extends Message implements
    AcceptableMessage {

  AcceptableMessage.Type type = AcceptableMessage.Type.REQUEST;
  User newUser;
  
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

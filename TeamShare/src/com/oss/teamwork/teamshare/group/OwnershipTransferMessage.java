package com.oss.teamwork.teamshare.group;

import com.oss.teamwork.teamshare.messaging.AcceptableMessage;
import com.oss.teamwork.teamshare.messaging.Message;

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

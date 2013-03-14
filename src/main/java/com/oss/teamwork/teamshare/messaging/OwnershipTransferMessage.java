package com.oss.teamwork.teamshare.messaging;

import com.oss.teamwork.teamshare.common.UserId;
import com.oss.teamwork.teamshare.team.User;

public class OwnershipTransferMessage extends Message implements
    AcceptableMessage {

  AcceptableMessage.Type type = AcceptableMessage.Type.REQUEST;
  UserId newUserId;
  
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

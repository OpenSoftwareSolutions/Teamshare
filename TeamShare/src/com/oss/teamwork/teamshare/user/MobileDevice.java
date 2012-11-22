package com.oss.teamwork.teamshare.user;

import java.net.InetSocketAddress;
import java.security.PublicKey;


import com.oss.teamwork.teamshare.common.ChangeId;
import com.oss.teamwork.teamshare.common.DeviceId;
import com.oss.teamwork.teamshare.sync.Change;

public class MobileDevice implements Device {

  @Override
  public DeviceId getDeviceId() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public InetSocketAddress getAddress() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isOnline() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public PublicKey getPublicKey() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void notifyChange(Change change) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void notifyMessageDelivery() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Change pullChange(ChangeId changeId) {
    // TODO Auto-generated method stub
    return null;
  }

}

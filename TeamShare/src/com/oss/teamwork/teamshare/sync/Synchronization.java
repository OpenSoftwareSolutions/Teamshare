package com.oss.teamwork.teamshare.sync;

import java.util.Collection;

import com.oss.teamwork.teamshare.io.File;
import com.oss.teamwork.teamshare.user.Device;

public class Synchronization {
  
  // TODO Decide how strategies are loaded from Configuration.
  protected PushStrategy pushStrategy;
  protected PullStrategy pullStrategy;
  
  public void pushFileMutation(File file, MutationType mutationType) {
    Change change = createChange(file, mutationType);
    
    Collection<Device> devices =
        pushStrategy.getDevices(change.getChangedGroup());
    
    for (Device device : devices) {
      device.notifyChange(change);
    }
  }
  
  public void pull() {
    
  }
  
  protected Change createChange(File file, MutationType mutationType) {
    // TODO Create Change from a file mutation here.
    return null;
  }
}

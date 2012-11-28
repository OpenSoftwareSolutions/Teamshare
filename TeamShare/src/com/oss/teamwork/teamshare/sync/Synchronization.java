package com.oss.teamwork.teamshare.sync;

import java.util.Collection;

import com.oss.teamwork.teamshare.io.FilesystemEvent;
import com.oss.teamwork.teamshare.user.Device;

public class Synchronization {
  
  // TODO Decide how strategies are loaded from Configuration.
  protected PushStrategy pushStrategy;
  protected PullStrategy pullStrategy;
  protected Change change;
  
  public void notifyFilesystemEvent(FilesystemEvent event) {
    change = createChange(event);
    push();
  }
  
  protected void push() {
    Collection<Device> devices =
        pushStrategy.getDevices(change.getChangedGroup());
    
    for (Device device : devices) {
      device.notifyChange(change);
    }
  }
  
  public void pull() {
    
  }
  
  protected Change createChange(FilesystemEvent event) {
    // TODO Create Change from a file mutation here.
    return null;
  }
}

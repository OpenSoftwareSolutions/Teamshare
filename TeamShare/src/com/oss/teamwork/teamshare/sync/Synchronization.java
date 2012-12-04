package com.oss.teamwork.teamshare.sync;
import com.oss.teamwork.teamshare.group.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import com.oss.teamwork.teamshare.common.Configuration;

import com.oss.teamwork.teamshare.io.FilesystemEvent;
import com.oss.teamwork.teamshare.user.Device;

public class Synchronization {
  
  // TODO Decide how strategies are loaded from Configuration.
  protected PushStrategy pushStrategy;
  protected PullStrategy pullStrategy;
  protected HashMap<Group, Change> changes;
  protected Timer pushScheduler;
  protected Timer pullScheduler;
  
  class PushTask extends TimerTask{

    @Override
    public void run() {
      for (Group group: changes.keySet()){
        Change change = changes.get(group);
        if (!change.isEmpty()){
          push(change);
     
          changes.put(group, new Change());
        }
    }
    }
  }
  
  class PullTask extends TimerTask{

    @Override
    public void run() {
        pull();
    
    }
  }
  
  public Synchronization(){
    
    pushScheduler = new Timer();
    pushScheduler.schedule(new PushTask(), 0, Configuration.getInstance().getPushInterval());
    
    pullScheduler = new Timer();
    pullScheduler.schedule(new PullTask(), 0, Configuration.getInstance().getPullInterval());
  }
  
  public void notifyFilesystemEvent(FilesystemEvent event) {
    updateChange(event);
    
  }
  
  protected void push(Change change) {
    Collection<Device> devices =
        pushStrategy.getDevices(change.getChangedGroup());
    
    for (Device device : devices) {
      device.notifyChange(change);
    }
  }
  
  public void pull() {
    
  }
  
  protected void updateChange(FilesystemEvent event) {

    Group group = GroupRepositoryFactory.getInstance().getGroupRepository().getGroup(event.getFile());
    changes.get(group).update(event);
    
  }
  
  
}

package com.oss.teamwork.teamshare.sync;

import java.util.Collection;

import com.oss.teamwork.teamshare.group.Device;
import com.oss.teamwork.teamshare.group.GroupRepository;

public class SwarmPushStrategy implements PushStrategy {

  // TODO Decide how specialized singletons are loaded from Configuration.
  protected GroupRepository groupRepository;

  /**
   * Force the others to pull, by notifying them.
   * 
   * @param change
   */
  public void push(Change change) {
    Collection<Device> devices = change.getChangedGroup().getGroupSwarm()
        .getDevices();
    for (Device device : devices) {
      device.notifyChange(change);
    }
  }

}

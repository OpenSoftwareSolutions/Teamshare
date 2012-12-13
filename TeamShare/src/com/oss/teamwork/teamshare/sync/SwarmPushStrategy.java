package com.oss.teamwork.teamshare.sync;

import java.util.Collection;

import com.oss.teamwork.teamshare.group.Device;

public class SwarmPushStrategy implements PushStrategy {

  // TODO Decide how specialized singletons are loaded from Configuration.

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

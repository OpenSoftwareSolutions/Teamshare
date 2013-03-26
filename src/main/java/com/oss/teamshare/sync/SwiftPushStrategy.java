package com.oss.teamshare.sync;

import java.util.Collection;

import com.oss.teamshare.communication.SwiftService;
import com.oss.teamshare.team.Device;
import com.oss.teamshare.team.Session;

public class SwiftPushStrategy implements PushStrategy {

  protected SwiftService swiftService;
  
  public SwiftPushStrategy() {
    swiftService = new SwiftService();
  }

  /**
   * Force the others to pull, by notifying them.
   * 
   * @param change
   */
  @Deprecated
  public void push(Change change) {
//    Collection<Device> devices = change.getChangedGroup().getSwarm()
//        .getDevices();
//    for (Device device : devices) {
//      device.notifyChange(change);
//    }
  }
  
  public void push(Revision revision) {
    // Create a new swarm for the new revision.
    swiftService.seed(revision.getFilename());
    
    // Get online devices.
    Collection<Device> onlineDevices = revision.getTeam().getOnlineDevices();
  }

}

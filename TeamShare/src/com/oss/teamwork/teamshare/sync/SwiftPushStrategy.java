package com.oss.teamwork.teamshare.sync;

import java.util.Collection;

import com.oss.teamwork.teamshare.group.DevicesQuery;
import com.oss.teamwork.teamshare.group.Group;
import com.oss.teamwork.teamshare.group.GroupRepository;
import com.oss.teamwork.teamshare.user.Device;

public class SwiftPushStrategy implements PushStrategy {
  
  // TODO Decide how specialized singletons are loaded from Configuration.
  protected GroupRepository groupRepository;

  /**
   * Force the others to pull, by notifying them.
   * @param change
   */
  public void push(Change change){
    Collection<Device> devices = getDevices(change.getChangedGroup());
    for (Device device : devices) {
      device.notifyChange(change);
    }
  }
  
  protected Collection<Device> getDevices(Group group) {
    DevicesQuery query = createDevicesQuery();
    return groupRepository.getDevices(group, query); 
  }
     
  protected  DevicesQuery createDevicesQuery(){
    return null;
  }
}

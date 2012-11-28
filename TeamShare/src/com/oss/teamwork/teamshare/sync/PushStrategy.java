package com.oss.teamwork.teamshare.sync;

import java.util.Collection;

import com.oss.teamwork.teamshare.group.DevicesQuery;
import com.oss.teamwork.teamshare.group.Group;
import com.oss.teamwork.teamshare.group.GroupRepository;
import com.oss.teamwork.teamshare.user.Device;

public abstract class PushStrategy implements OverlayStrategy {
  
  // TODO Decide how specialized singletons are loaded from Configuration.
  protected GroupRepository groupRepository;
  
  @Override
  public Collection<Device> getDevices(Group group) {
    DevicesQuery query = createDevicesQuery();
    return groupRepository.getDevices(group, query);
  }
  
  public abstract DevicesQuery createDevicesQuery();
}

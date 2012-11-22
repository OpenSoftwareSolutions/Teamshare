package com.oss.teamwork.teamshare.group;

import java.util.List;

import com.oss.teamwork.teamshare.user.Device;
import com.oss.teamwork.teamshare.user.User;

public interface GroupRepository {

  /**
   * Returns a list of devices from the group based on a query criteria (e.g devices for 
   * @param group
   * @param query
   * @return
   */
  public List<Device> getDevices(Group group, DevicesQuery query);
  
  public List<Device> addDevice(User user, Device device);
  
}

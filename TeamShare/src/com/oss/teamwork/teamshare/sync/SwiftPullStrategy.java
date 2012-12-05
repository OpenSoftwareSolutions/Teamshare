package com.oss.teamwork.teamshare.sync;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.oss.teamwork.teamshare.group.DevicesQuery;
import com.oss.teamwork.teamshare.group.Group;
import com.oss.teamwork.teamshare.group.GroupRepository;
import com.oss.teamwork.teamshare.user.Device;

public class SwiftPullStrategy implements PullStrategy{

  Map<Group, Collection<Device>> devices;
  protected GroupRepository groupRepository;
  
  @Override
  public Collection<Change> pull(Group group) {
    
    Map<Version, Collection<Device>> versions = new  LinkedHashMap<Version, Collection<Device>>();
    
    for (Device device: devices.get(group)) {
      Version v = device.getVersion();
      Collection<Device> devices = versions.get(v);
      if (devices == null) {
        devices = new ArrayList<Device>();
      }
      devices.add(device);
      versions.put(v, devices);
    }   
    //todo map-ul invers
    
    return null;
  }
  
  protected Collection<Device> getDevices(Group group) {
    DevicesQuery query = createDevicesQuery();
    return groupRepository.getDevices(group, query); 
  }
     
  protected  DevicesQuery createDevicesQuery(){
    return null;
  }

}

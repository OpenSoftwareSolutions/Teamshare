package com.oss.teamwork.teamshare.sync;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.oss.teamwork.teamshare.group.Device;
import com.oss.teamwork.teamshare.group.Group;
import com.oss.teamwork.teamshare.group.GroupRepository;

public class SwiftPullStrategy implements PullStrategy {

  protected GroupRepository groupRepository;
  protected VersioningStrategy versioningStrategy;
  
  

  public SwiftPullStrategy() {
    super();
    
    // TODO init GroupRepository, VersioningStrategy
  }

  @Override
  public Collection<Change> pull(Group group) {

    Map<Version, Collection<Device>> versions =
        new LinkedHashMap<Version, Collection<Device>>();
    Version chosenVersion;

    Collection<Device> devices = group.getGroupSwarm().getDevices();
    for (Device device : devices) {
      Version v = device.getVersion();
      Collection<Device> versionDevices = versions.get(v);
      if (versionDevices == null) {
        versionDevices = new ArrayList<Device>();
      }
      versionDevices.add(device);
      versions.put(v, versionDevices);
    }
    
    // Choose version.
    chosenVersion = versioningStrategy.chooseVersion(versions.keySet());
    
    // TODO do the actual pull by creating a swift Swarm.

    return null;
  }

}

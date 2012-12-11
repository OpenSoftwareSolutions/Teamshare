package com.oss.teamwork.teamshare.sync;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.oss.teamwork.teamshare.communication.Swarm;
import com.oss.teamwork.teamshare.group.Device;
import com.oss.teamwork.teamshare.group.Group;
import com.oss.teamwork.teamshare.group.GroupRepository;

public abstract class SwarmPullStrategy implements PullStrategy {

  protected GroupRepository groupRepository;
  protected VersioningStrategy versioningStrategy;

  public SwarmPullStrategy() {
    super();

    // TODO init GroupRepository, VersioningStrategy
  }

  @Override
  public Collection<Change> pull(Group group) {

    Map<Version, Collection<Device>> versions = new LinkedHashMap<Version, Collection<Device>>();
    Version chosenVersion;
    Swarm swarm;

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
    swarm = createSwarm(group, chosenVersion);
    swarm.start();

    // TODO refactor for an asynchronous download of changes
    return null;
  }

  /**
   * Create a swarm instance from a group folder version.
   * 
   * @param group
   *          group which requires a swarm to be created in order to propagate
   *          its change
   * @param version
   *          version of the group folder for which a swarm is required
   * @return a newly created swarm
   */
  protected abstract Swarm createSwarm(Group group, Version version);

}

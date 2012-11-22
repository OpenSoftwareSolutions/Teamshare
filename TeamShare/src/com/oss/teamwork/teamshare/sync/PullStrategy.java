package com.oss.teamwork.teamshare.sync;

import java.util.Collection;

import com.oss.teamwork.teamshare.common.OverlayStrategy;
import com.oss.teamwork.teamshare.group.Group;
import com.oss.teamwork.teamshare.user.Device;

public class PullStrategy implements OverlayStrategy {

  @Override
  public Collection<Device> getDevices(Group group) {
    return null;
  }

}

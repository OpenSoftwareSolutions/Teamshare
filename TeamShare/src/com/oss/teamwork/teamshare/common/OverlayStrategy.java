package com.oss.teamwork.teamshare.common;

import java.util.Collection;

import com.oss.teamwork.teamshare.group.Group;
import com.oss.teamwork.teamshare.user.Device;

public interface OverlayStrategy {
  Collection<Device> getDevices(Group group);
}

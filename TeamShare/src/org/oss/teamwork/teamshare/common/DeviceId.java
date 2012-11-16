package org.oss.teamwork.teamshare.common;

public class DeviceId extends Id {

  protected DeviceId() {
  }

  protected DeviceId(byte[] hash) {
    super(hash);
  }

  // TODO create device ID based on some criteria
  public static DeviceId createDeviceId() {
    return new DeviceId(null);
  }
}

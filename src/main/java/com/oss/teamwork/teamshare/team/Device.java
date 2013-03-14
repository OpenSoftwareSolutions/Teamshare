package com.oss.teamwork.teamshare.team;

import java.net.InetSocketAddress;
import java.security.PublicKey;

import com.oss.teamwork.teamshare.common.ChangeId;
import com.oss.teamwork.teamshare.common.DeviceId;
import com.oss.teamwork.teamshare.sync.Change;
import com.oss.teamwork.teamshare.sync.Version;

public abstract class Device {

  /**
   * Device's unique identifier, provided when the device is linked. It is not
   * visible to the user.
   */
  DeviceId id;

  /**
   * The device's name, selected by the user when linking the device. It is
   * visible to the user when visualizing the list of his devices. It must be
   * unique for each user.
   */
  String name;

  /**
   * The device's connectivity information: IP address and port. This field is
   * non-null while the device in online and null while offline.
   */
  InetSocketAddress address;

  /**
   * The user which owns the device.
   */
  User user;

  /**
   * Whether the user is online (true) or not (false). A device is online when
   * it can connect and communicate with other devices.
   */
  boolean online;

  /**
   * Return {@code true} if {@code this} is a mobile device and {@code false}
   * otherwise.
   */
  boolean mobile;

  /**
   * For security mechanisms, the devices may also have one ore more keys. The
   * exact type and usage is described at application level.
   */
  PublicKey publicKey;

  /**
   * Notify a device of a change so that it can pull them.
   */
  public abstract void notifyChange(Change change);

  /**
   * Notify a device that a message has been sent to it so that it can check its
   * mailbox.
   */
  public abstract void notifyMessageDelivery();

  /**
   * Pull a change identified by its ID from this device.
   */
  abstract Change pullChange(ChangeId changeId);

  public DeviceId getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public User getUser() {
    return user;
  }

  public boolean isOnline() {
    return online;
  }

  public boolean isMobile() {
    return mobile;
  }

  @Deprecated
  abstract public Version getVersion();
}

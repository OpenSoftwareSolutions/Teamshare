package com.oss.teamwork.teamshare.user;

import java.net.InetSocketAddress;
import java.security.PublicKey;


import com.oss.teamwork.teamshare.common.ChangeId;
import com.oss.teamwork.teamshare.common.DeviceId;
import com.oss.teamwork.teamshare.sync.Change;

public interface Device {
  
  /*  ______________________________________________________________________
   * | GETTERS                                                              |
   * |______________________________________________________________________|
  */
  
  /**
   * The device's unique identifier, provided when the device is linked.
   * It is not visible to the user.
   */
  DeviceId getDeviceId();
  
  /**
   * The device's name, selected by the user when linking the device.
   * It is visible to the user when visualizing the list of his devices.
   * It must be unique for each user.
   */
  String getName();
  
  /**
   * The device's connectivity information: IP address and port. 
   * This field is non-null while the device in online and null while offline.
   */
  InetSocketAddress getAddress();
  
  /**
   * The device's status: true if online, false if offline.
   * A device is online when it can connect and communicate with other devices.
   */
  boolean isOnline();
  
  /**
   * For security mechanisms, the devices may also have one ore more keys. The exact type and usage is described at application level.
   */
  PublicKey getPublicKey();
  
  
  /*  ______________________________________________________________________
   * | OPERATIONS                                                           |
   * |______________________________________________________________________|
  */
  
  // TODO is method asynchronous? should it return a handler?
  /**
   * Notify a device of a change so that it can pull them.
   */
  void notifyChange(Change change);
  
  /**
   * Notify a device that a message has been sent to it so that it can check
   * its mailbox.
   */
  void notifyMessageDelivery();
  
  /**
   * Pull a change identified by its ID from this device.
   */
  Change pullChange(ChangeId changeId);
}

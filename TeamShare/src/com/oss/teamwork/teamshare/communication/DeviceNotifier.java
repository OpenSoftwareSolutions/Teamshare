package com.oss.teamwork.teamshare.communication;


import com.oss.teamwork.teamshare.common.DeviceId;
import com.oss.teamwork.teamshare.sync.Change;

/**
 * Provides RPC-like calls for notifications to another device.
 */
public interface DeviceNotifier {
  
  /*  ______________________________________________________________________
   * | GETTERS                                                              |
   * |______________________________________________________________________|
  */
  
  /**
   * The device's unique identifier, provided when the device is linked.
   * It is not visible to the user.
   */
  DeviceId getDeviceId();
  
  
  /*  ______________________________________________________________________
   * | OPERATIONS                                                           |
   * |______________________________________________________________________|
  */
  
  /**
   * Notify a device of a change so that it can pull them.
   */
  void notifyChange(Change change);
  
  /**
   * Notify a device that a message has been sent to it so that it can check
   * its mailbox.
   */
  void notifyMessageDelivery();
}

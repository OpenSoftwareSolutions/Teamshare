package com.oss.teamwork.teamshare.communication;


import com.oss.teamwork.teamshare.sync.Change;
import com.oss.teamwork.teamshare.team.DeviceId;

/**
 * Provides RPC-like calls for notifications to another device.
 */
public interface DeviceNotifier {
  
  // GETTERS
  // =======
  
  /**
   * The device's unique identifier, provided when the device is linked.
   * It is not visible to the user.
   */
  DeviceId getDeviceId();
  
  
  // OPERATIONS
  // ==========
  
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

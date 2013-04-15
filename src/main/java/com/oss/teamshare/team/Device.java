package com.oss.teamshare.team;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.security.PublicKey;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oss.teamshare.common.ChangeId;
import com.oss.teamshare.communication.IceRuntime;
import com.oss.teamshare.communication.zerocice.DeviceEndpointPrx;
import com.oss.teamshare.communication.zerocice.DeviceEndpointPrxHelper;
//import com.oss.teamshare.sync.Change;
//import com.oss.teamshare.sync.Revision;

public class Device {
  
  public Device(DeviceId id, String name, User user, InetAddress address,
      int icePort, int swiftPort) {
    super();
    this.id = id;
    this.name = name;
    this.user = user;
    this.address = address;
    this.icePort = icePort;
    this.swiftPort = swiftPort;
  }

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
   * Internet socket address for the communication endpoint of the device.
   */
  InetAddress address;
  
  int icePort;
  
  int swiftPort;
  
  /**
   * Provides communication to another device through Ice.
   */
  DeviceEndpointPrx endpoint;
  
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
  
  private static Logger logger = LogManager.getLogger(Device.class);
  
  /**
   * Connect to the device endpoint by using Ice. endpoint member will hold
   * a reference to the device proxy.
   * 
   * TODO Return an exception if the connection fails.
   */
  public void connect() {
    if (endpoint != null) {
      return;
    }
    
    Ice.ObjectPrx base = IceRuntime.getInstance().createObjectProxy(id,
        new InetSocketAddress(address, icePort));
    endpoint = DeviceEndpointPrxHelper.checkedCast(base);
  }

  /**
   * Notify a device of a change so that it can pull them.
   */
//  public void notifyChange(Change change) {
//    
//  }

  /**
   * Notify a device that a message has been sent to it so that it can check its
   * mailbox.
   */
  public void notifyMessageDelivery() {
    
  }

  /**
   * Pull a change identified by its ID from this device.
   */
//  Change pullChange(ChangeId changeId) {
//    return null;
//  }

  public DeviceId getId() {
    return id;
  }

  public String getName() {
    return name;
  }
  
  public DeviceEndpointPrx getEndpoint() {
    connect();
    return endpoint;
  }

  public User getUser() {
    return user;
  }

  public InetAddress getAddress() {
    return address;
  }

  public int getIcePort() {
    return icePort;
  }

  public int getSwiftPort() {
    return swiftPort;
  }

  public boolean isOnline() {
    return online;
  }

  public boolean isMobile() {
    return mobile;
  }

  // TODO
//  public Revision getVersion() {
//    return null;
//  }

  @Override
  public String toString() {
    return "" + id;
  }
  
}

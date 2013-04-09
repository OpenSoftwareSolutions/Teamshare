package com.oss.teamshare.team;

import java.util.LinkedHashMap;
import java.util.Map;


public class User extends Person {

  
  
  public User(UserId id, String username) {
    super();
    this.id = id;
    this.username = username;
  }

  /**
   * The user's unique identifier.
   */
  protected UserId id;

  /**
   * The user's unique username. It is the one visible to other users through a
   * user interface.
   */
  protected String username;

  /**
   * Collection of all devices linked by the user. This Collection is not
   * visible to the user. This information is vital for synchronizing changes.
   */
  protected Map<DeviceId, Device> devices = new LinkedHashMap<>();
  
  void addDevice(Device device) {
    if (device != null) {
      devices.put(device.getId(), device);
    }
  }

  public UserId getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public Map<DeviceId, Device> getDevices() {
    return devices;
  }

  @Override
  public String toString() {
    String strDevices = "";
    
    for (Device device : devices.values()) {
      strDevices += device + ", ";
    }
    
    return String.format("User(%s, %s, [%s])", id, username, strDevices);
  }
}

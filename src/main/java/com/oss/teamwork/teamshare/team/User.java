package com.oss.teamwork.teamshare.team;

import java.util.Collection;

import com.oss.teamwork.teamshare.common.UserId;

public class User extends Person {

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
  protected Collection<Device> devices;

  public UserId getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public Collection<Device> getDevices() {
    return devices;
  }
}

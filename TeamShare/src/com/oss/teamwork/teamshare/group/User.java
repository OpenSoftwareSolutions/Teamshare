package com.oss.teamwork.teamshare.group;

import java.util.Collection;

import com.oss.teamwork.teamshare.common.UserId;

public class User {

  /**
   * The user's unique identifier. It is not visible through the user interface. <br>
   * Is the same as the one in the user's Account.
   */
  protected UserId userID;

  /**
   * The user's username. It is the one visible to other users through an user
   * interface. It is also unique. <br>
   * It's the same as the one in the user's Account.
   */
  protected String username;

  /**
   * The user's email address, used for receiving notifications. Two users
   * cannot have the same email address.
   */
  protected String email;

  /**
   * TODO personal info object User's personal information, not having a major
   * role in the application yet.
   */
  protected String personalInfo;

  /**
   * The user's status, which can be seen by other users. True if online (at
   * least one of its devices is online), false otherwise.
   */
  protected boolean online;

  /**
   * Collection of all devices linked by the user. This Collection is not visible to the
   * user. This information is vital for synchronizing changes.
   */
  protected Collection<Device> devices;

  
  /*  ______________________________________________________________________
   * | Getters and Setters                                                  |
   * |______________________________________________________________________|
  */
  
  public UserId getUserID() {
    return userID;
  }

  public void setUserID(UserId userID) {
    this.userID = userID;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Check if a user is online or not. The user is seen as online if at least
   * one of its devices is online. See application's policies regarding
   * anonymity, what is visible to other users and what's not.
   *  
   * @return True if the user is online, False otherwise
   * 
   */
  public boolean isOnline() {
    return online;
  }

  public void setOnline(boolean online) {
    this.online = online;
  }

  public Collection<Device> getDevices() {
    return devices;
  }

  public void setDevices(Collection<Device> devices) {
    this.devices = devices;
  }

  public String getPersonalInfo() {
    return personalInfo;
  }

  public void setPersonalInfo(String personalInfo) {
    this.personalInfo = personalInfo;
  }
}

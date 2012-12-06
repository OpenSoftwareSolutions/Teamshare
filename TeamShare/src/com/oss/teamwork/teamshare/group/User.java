package com.oss.teamwork.teamshare.group;

import java.util.List;

import com.oss.teamwork.teamshare.common.UserId;


public class User {

  /**
   * The user's unique identifier. It is not visible through the user interface.
   * <br>Is the same as the one in the user's Account.
   */
  public UserId userID;

  /**
   * The user's username. It is the one visible to other users through an user interface.
   * It is also unique.
   * <br>It's the same as the one in the user's Account.
   */
  public String username;

  /**
   * The user's email address, used for receiving notifications. Two users cannot have the same email address.
   * <br>It's the same as the one in the user's Account.
   */
  public String email;

  /**
   * The user's status, which can be seen by other users. True if online (at least one of its devices is online), false otherwise.
   */
  public boolean online;

  /**
   * List of all devices linked by the user. This list is not visible to the user. 
   * This information is vital for synchronizing changes.
   */
  public List<Device> devices;
}

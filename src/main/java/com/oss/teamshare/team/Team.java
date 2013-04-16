package com.oss.teamshare.team;

import com.oss.teamshare.communication.Swarm;
//import com.oss.teamshare.sync.Revision;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * The Team entity is defined by an unique ID and its team folder. In addition
 * it holds attributes such as the team owner, its list of users, security keys
 * and settings.
 * 
 */
public class Team {
  
  /**
   * The team's unique identifier. It is not visible to users through the
   * application's interface. It is used only internally to identify teams.
   */
  protected TeamId id;
  
  /**
   * Team friendly, descriptive name.
   */
  protected String name;

  /**
   * The team folder's name is also the team's actual name. This folder is the
   * root for all the files and folders created by the team's users.
   */
  protected Path path;

  /**
   * The team's owner.
   */
  protected User owner;

  /**
   * The team's users.
   */
  protected Map<UserId, User> users = new LinkedHashMap<>();

  /**
   * The team's settings. Settings are implementation dependent. Exact settings
   * or their number is not set at domain level. They should include properties
   * such as the maximum number of users and the maximum storage capacity.
   */
  protected Properties settings;

  /**
   * The (locally-stored) version of the team's folder. Ideally it would be
   * the same for all the devices in the team.
   */
//  protected Revision version;
  
  protected Swarm swarm;
  
  public Team(TeamId id, String name, User owner) {
    this.id = id;
    this.name = name;
    this.owner = owner;
    
    addUser(owner);
  }
  
  public Collection<Device> getOnlineDevices() {
    Collection<Device> devices = new ArrayList<>();
    
    for (User user: users.values()) {
      devices.addAll(user.devices.values());
    }
    
    return devices;
  }
  
  void addUser(User user) {
    if (user != null) {
      users.put(user.getId(), user);
    }
  }
  
  boolean hasUser(UserId id) {
    return users.containsKey(id);
  }
  
  /**
   * Invites an user into the given team.
   * 
   * @param user the invited user
   */
  public void invite(User user) {
  }

  /**
   * Invites an external person into the given team.
   * 
   * @param email the external person's email address, to which the invitation is
   *          sent
   */
  public void invite(String email) {
  }

  /**
   * Starts performing the necessary steps for ownership transfer.
   * 
   * @param newOwner the proposed team owner
   */
  public void transferOwnership(User newOwner) {
  }

  /**
   * Starts performing the necessary steps for removing an user from this team.
   * 
   * @param user the user to be removed
   */
  public void removeUser(User user) {
  }

  /**
   * Applies the given team settings.
   * 
   * @param settings
   */
  public void changeSettings(Properties newSettings) {
  }

  /**
   * Starts performing the necessary steps for removing the current user (the
   * logged-in user) from this team.
   */
  public void leave() {
  }

  /**
   * Removes this team. Can be done only by the owner (the logged-in user must
   * be the team's owner) and only when the team doesn't have any other
   * members.
   */
  public void removeTeam() {
  }
  
  public void acceptInvitation(User user) {
    // TODO
    
    // addUser, removePending
  }
  
  public void refuseInvitation(User user) {
    // TODO
    
    //removePending
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Team other = (Team) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
  
  @Override
  public String toString() {
    String strUsers = "";
    for (User user : users.values()) {
      strUsers += user + ", ";
    }
    return String.format("Team(%s, %s, %s, [%s])", id, name, owner, strUsers);
  }

  public Swarm getSwarm() {
    return swarm;
  }

  public TeamId getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Path getPath() {
    if (path == null) {
      return Paths.get(name);
    }
    
    return path;
  }

  public User getOwner() {
    return owner;
  }
  
}

package com.oss.teamwork.teamshare.group;

import com.oss.teamwork.teamshare.common.*;
import com.oss.teamwork.teamshare.communication.Swarm;
import com.oss.teamwork.teamshare.io.Folder;
import com.oss.teamwork.teamshare.sync.Change;
import com.oss.teamwork.teamshare.sync.Version;

import java.util.List;
import java.util.Properties;

/**
 * The Group entity is defined by an unique ID and its group folder. In addition
 * it holds attributes such as the group owner, its list of users, security keys
 * and settings.
 * 
 */
public class Group {
  /**
   * The group's unique identifier. It is not visible to users through the
   * application's interface. It is used only internally to identify groups.
   */
  protected GroupId groupID;

  /**
   * The group folder's name is also the group's actual name. This folder is the
   * root for all the files and folders created by the group's users.
   */
  protected Folder folderName;

  /**
   * The group's owner.
   */
  protected User owner;

  /**
   * A user that will become the group's owner after an ownership transfer. It
   * is marked as pending only during the transfer.
   */
  protected User pendingOwner;

  /**
   * The group's users.
   */
  protected List<User> users;

  /**
   * The group's settings. Settings are implementation dependent. Exact settings
   * or their number is not set at domain level. They should include properties
   * such as the maximum number of users and the maximum storage capacity.
   */
  protected Properties settings;

  /**
   * The (locally-stored) version of the group's folder. Ideally it would be
   * the same for all the devices in the group.
   */
  protected Version version;
  
  protected Swarm groupSwarm;
  
  
  /**
   * Invites an user into the given group.
   * 
   * @param user
   *          - the invited user
   */
  public void invite(User user) {
  }

  /**
   * Invites an external person into the given group.
   * 
   * @param email
   *          - the external person's email address, to which the invitation is
   *          sent
   */
  public void invite(String email) {
  }

  /**
   * Starts performing the necessary steps for ownership transfer.
   * 
   * @param newOwner
   *          - the proposed group owner
   */
  public void transferOwnership(User newOwner) {
  }

  /**
   * Starts performing the necessary steps for removing an user from this group.
   * 
   * @param user
   *          - the user to be removed
   */
  public void removeUser(User user) {
  }

  /**
   * Applies the given group settings.
   * 
   * @param settings
   */
  public void changeSettings(Properties newSettings) {
  }

  /**
   * Starts performing the necessary steps for removing the current user (the
   * logged-in user) from this group.
   */
  public void leave() {
  }

  /**
   * Removes this group. Can be done only by the owner (the logged-in user must
   * be the group's owner) and only when the group doesn't have any other
   * members.
   */
  public void removeGroup() {
  }

  /**
   * Applies the new group change.
   * 
   * @param groupChange
   *          - the change that was received for this group from another
   *          device(s) in the group
   */

	public void applyChange(Change groupChange){}

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((groupID == null) ? 0 : groupID.hashCode());
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
    Group other = (Group) obj;
    if (groupID == null) {
      if (other.groupID != null)
        return false;
    } else if (!groupID.equals(other.groupID))
      return false;
    return true;
  }

  public Swarm getGroupSwarm() {
    return groupSwarm;
  }
  
}

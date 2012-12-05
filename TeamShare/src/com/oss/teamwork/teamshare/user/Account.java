package com.oss.teamwork.teamshare.user;

import java.util.Collection;

import com.oss.teamwork.teamshare.group.Group;
import com.oss.teamwork.teamshare.messaging.Mailbox;


/**
 * A Teamshare user. Other users have access/create the {@link User} object of that user, not the Account.
 *
 */
public class Account {
	/**
	 * The user's unique identifier. The identifier is not visible through the user interface.
	 */
	protected long userID;
	/**
	 * The user's username. The username the one visible to other users through an user interface.
	 * Similar to the userID, it is unique.
	 */
	protected String username;
	/**
	 * The user's email address, the one for receiving notifications. Two users cannot have the same email address.
	 */
	protected String email;
	/**
	 * The groups the user is member of.
	 */
	protected Collection<Group> myGroups;
	/**
	 * The groups the user is owner of (it is a subset of mygroups).
	 */
	protected Collection<Group> ownGroups;
	
  /**
	 * The devices the user has linked into the system.
	 */
	protected Collection<Device> devices;
	/**
	 * User's personal information, not having a major role in the application yet so is not defined at this level.
	 */
	protected String personalInfo;
	/**
	 * A reference to the user's mailbox, a repository that stores and retrieves the user's messages.
	 */
	protected Mailbox mailbox;
	
	
	public long getUserID() {
    return userID;
  }
  public void setUserID(long userID) {
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
  public Collection<Group> getMyGroups() {
    return myGroups;
  }
  public void setMyGroups(Collection<Group> myGroups) {
    this.myGroups = myGroups;
  }
  public Collection<Group> getOwnGroups() {
    return ownGroups;
  }
  public void setOwnGroups(Collection<Group> ownGroups) {
    this.ownGroups = ownGroups;
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
  public Mailbox getMailbox() {
    return mailbox;
  }
  public void setMailbox(Mailbox mailbox) {
    this.mailbox = mailbox;
  }
}

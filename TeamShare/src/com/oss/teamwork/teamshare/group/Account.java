package com.oss.teamwork.teamshare.group;

import java.util.Collection;

import com.oss.teamwork.teamshare.messaging.Mailbox;

/**
 * A Teamshare user. Other users have access/create the {@link User} object of
 * that user, not the Account.
 * 
 */
public class Account {

  protected User myUser;
  /**
   * The user's username. The username the one visible to other users through an
   * user interface. Similar to the userID, it is unique.
   */
  protected String username;
  /**
   * The user's email address, the one for receiving notifications. Two users
   * cannot have the same email address.
   */
  protected String email;
  /**
   * The groups the user is member of.
   */
  protected Collection<Group> myGroups;
  /**
   * The groups the user is owner of (it is a subset of mygroups).
   */
  protected Collection<Group> ownedGroups;

  /**
   * TODO personal info object
   * User's personal information, not having a major role in the
   * application yet so is not defined at this level.
   */
  protected String personalInfo;
  /**
   * A reference to the user's mailbox, a repository that stores and retrieves
   * the user's messages.
   */
  protected Mailbox mailbox;

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
    return ownedGroups;
  }

  public void setOwnGroups(Collection<Group> ownGroups) {
    this.ownedGroups = ownGroups;
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

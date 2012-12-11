package com.oss.teamwork.teamshare.group;

import java.util.Collection;

import com.oss.teamwork.teamshare.messaging.Mailbox;

/**
 * Represents the account of the logged-in Teamshare user. Other users have
 * access/create the {@link User} object of that user, not the Account. The
 * Account stores more information than an {@link User} object, such as the
 * number of groups the user is member of, which is not visible to other users.
 * 
 */
public class Account {

  /**
   * The {@link User} object for this account, containing all the public
   * information for this user.
   */
  protected User myUser;

  /**
   * The groups the user is member of.
   */
  protected Collection<Group> myGroups;
  
  /**
   * The groups the user is owner of (it is a subset of mygroups).
   */
  protected Collection<Group> ownedGroups;

  /**
   * A reference to the user's mailbox, a repository that stores and provides
   * access to the logged-in user's messages.
   */
  protected Mailbox mailbox;

  /*  ______________________________________________________________________
   * | Getters and Setters                                                  |
   * |______________________________________________________________________|
  */
  
  
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

  public Mailbox getMailbox() {
    return mailbox;
  }

  public void setMailbox(Mailbox mailbox) {
    this.mailbox = mailbox;
  }
}

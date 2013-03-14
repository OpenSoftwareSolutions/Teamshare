package com.oss.teamwork.teamshare.team;

import java.util.Collection;

import com.oss.teamwork.teamshare.messaging.Mailbox;

/**
 * Singleton representing the session of running the application on a device. 
 */
public class Session {

  /**
   * Reference to the device of the current logged in user.
   */
  protected Device device;

  /**
   * The groups the user is member of.
   */
  protected Collection<Team> teams;

  /**
   * The groups the user is owner of (it is a subset of mygroups).
   * TODO Redundant. Is it required?
   */
  protected Collection<Team> ownedTeams;

  /**
   * Reference to the user's mailbox, a repository that stores and provides
   * access to the logged-in user's messages.
   */
  protected Mailbox mailbox;

  public Collection<Team> getTeams() {
    return teams;
  }

  public Collection<Team> getOwnedTeams() {
    return ownedTeams;
  }

  public Mailbox getMailbox() {
    return mailbox;
  }
}

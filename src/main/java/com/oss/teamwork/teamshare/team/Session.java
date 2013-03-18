package com.oss.teamwork.teamshare.team;

import java.util.Map;

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
  protected Map<TeamId, Team> teams;

  /**
   * Reference to the user's mailbox, a repository that stores and provides
   * access to the logged-in user's messages.
   */
  protected Mailbox mailbox;

  public Map<TeamId, Team> getTeams() {
    return teams;
  }

  public Mailbox getMailbox() {
    return mailbox;
  }
}

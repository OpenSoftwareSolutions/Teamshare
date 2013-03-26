package com.oss.teamshare.team;

import java.util.Map;

import com.oss.teamshare.messaging.Mailbox;

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
  
  /**
   * Returns a team by its id or null if not found.
   * 
   * @param id
   * @return
   */
  public Team getTeam(TeamId id) {
    return teams.get(id);
  }

  public Mailbox getMailbox() {
    return mailbox;
  }
}

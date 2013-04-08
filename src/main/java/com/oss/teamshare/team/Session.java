package com.oss.teamshare.team;

import java.nio.file.Path;
import java.util.Map;

import com.oss.teamshare.io.TeamFile;
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
   * Teamshare path representing the Teamshare folder mounted after the user
   * authenticates. 
   */
  protected Path path;
  
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
  
  public Path getPath() {
    return path;
  }

  /**
   * Retrieves a Team reference by its path relative to Teamshare path.
   * 
   * @param path team folder path relative to Teamshare path
   * @return the team for that path or null if there is no team there
   */
  public Team getTeamByPath(Path path) {
    // TODO Optimize
    for (Team team: teams.values()) {
      if (path.startsWith(path)) {
        return team;
      }
    }
    
    return null;
  }

  /**
   * Returns a TeamFile instance from an absolute local file path.
   * 
   * @param absoluteFile the abstolute file path
   * @return the TeamFile instance
   */
  public TeamFile getTeamFile(Path absoluteFile) {
    Path pathInTeamshare = path.relativize(absoluteFile);
    Team team = getTeamByPath(pathInTeamshare);
    Path filePath = team.getPath().relativize(pathInTeamshare);
    
    return new TeamFile(team, filePath);
  }
}

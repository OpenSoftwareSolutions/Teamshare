package com.oss.teamshare.team;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oss.teamshare.communication.DeviceServer;
import com.oss.teamshare.communication.IceRuntime;
import com.oss.teamshare.io.TeamFile;
import com.oss.teamshare.messaging.Mailbox;

/**
 * Singleton representing the session of running the application on a device.
 */
public class Session implements AutoCloseable {

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

  private DeviceServer deviceServer;

  public static Logger logger = LogManager.getLogger(Session.class);

  /**
   * Demo constructor which load team from JSON files.
   * 
   * @param userId
   * @param deviceId
   */
  public Session(UserId userId, DeviceId deviceId, int port) {
    /* Start device server where other devices can connect through Ice. */
    deviceServer = new DeviceServer(deviceId, port);
    deviceServer.start();

    /* Retrieve teams from JSON. */
    TeamRepo teamRepo = new JsonTeamRepo();
    try {
      teams = teamRepo.retrieveUserTeams(userId, deviceId);
    } catch (TeamRepoException e) {
      logger.fatal("Failed to load teams from JSON files: " + e.getMessage());
      System.exit(1);
    }
    logger.info("Loaded " + teams.size() + " teams.");
    for (Team team : teams.values()) {
      logger.debug("" + team);
    }

    /*
     * Read Teamshare path from Java property teamshare.path if specified, or
     * use "${user.home}/Teamshare" otherwise.
     */
    String strPath = System.getProperty("teamshare.path");
    if (strPath == null) {
      path = Paths.get(System.getProperty("user.home"), "Teamshare");
    } else {
      path = Paths.get(strPath);
    }
    logger.info("Teamshare path: " + path);
  }

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
   * @param path
   *          team folder path relative to Teamshare path
   * @return the team for that path or null if there is no team there
   */
  public Team getTeamByPath(Path path) {
    // TODO Optimize
    for (Team team : teams.values()) {
      if (path.startsWith(team.getPath())) {
        return team;
      }
    }

    return null;
  }

  /**
   * Returns a TeamFile instance from an absolute local file path.
   * 
   * @param absoluteFile
   *          the abstolute file path
   * @return the TeamFile instance
   */
  public TeamFile getTeamFile(Path absoluteFile) {
    Path pathInTeamshare = path.relativize(absoluteFile);
    Team team = getTeamByPath(pathInTeamshare);
    if (team == null) {
      throw new Error(String.format("File '%s' does not belong to any team.",
          absoluteFile));
    }
    Path filePath = team.getPath().relativize(pathInTeamshare);

    return new TeamFile(team, filePath);
  }

  @Override
  public void close() throws Exception {
    IceRuntime.getInstance().close();
  }
}

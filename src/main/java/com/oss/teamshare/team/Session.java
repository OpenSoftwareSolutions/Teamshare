package com.oss.teamshare.team;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
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

  private static Session instance = null;

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

  public static Session create(UserId userId, DeviceId deviceId, int port) {
    if (instance != null) {
      throw new IllegalStateException("Session already created");
    }

    instance = new Session(userId, deviceId, port);
    return instance;
  }

  public static Session getInstance() {
    if (instance == null) {
      throw new IllegalStateException("The Session must be created first.");
    }

    return instance;
  }

  /**
   * Demo constructor which load team from JSON files.
   * 
   * @param userId
   * @param deviceId
   */
  private Session(UserId userId, DeviceId deviceId, int port) {
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
   *  Returns the Path to the team's hidden folder, which contains configuration details, file hashes etc.
   *  Only the application has access to this folder and it is not synchronized.
   *
   * @param team - the Team for which to obtain the path
   * @return
   */
  public Path getHiddenTeamFolder(Team team) {
    String s = path.toString() +  FileSystems.getDefault().getSeparator().charAt(0)+ team.getPath().toString() + FileSystems.getDefault().getSeparator().charAt(0)+".team";
    Path hiddenTeamFolder = FileSystems.getDefault().getPath(s);
    System.out.println(s + " " + hiddenTeamFolder);
    if (!Files.exists(hiddenTeamFolder) || !Files.isDirectory(hiddenTeamFolder)) {
      try{
        Files.createDirectory(hiddenTeamFolder); // for windows we need to make it hidden using attribute "dos:hidden"
      }catch(IOException ioe){
        logger.fatal(String.format("Failed to create team's hidden folder " +
            "with path %s:", hiddenTeamFolder, ioe.getMessage()));
        ioe.printStackTrace();
        System.exit(1);
      }
    }
    return hiddenTeamFolder;
  }
  
  public Path getHiddenTeamFilesFolder(Team team){
    Path hiddenTeamFolder = getHiddenTeamFolder(team);
    String s = hiddenTeamFolder.toString() + FileSystems.getDefault().getSeparator().charAt(0) + "files";
    Path hiddenTeamFilesFolder =   FileSystems.getDefault().getPath(s);   
    if (!Files.exists(hiddenTeamFilesFolder) || !Files.isDirectory(hiddenTeamFilesFolder)) {
          try{
            Files.createDirectory(hiddenTeamFilesFolder); // for windows we need to make it hidden using attribute "dos:hidden"
          }catch(IOException ioe){
            logger.fatal(String.format("Failed to create team's hidden folder " +
                "with path %s:", hiddenTeamFilesFolder, ioe.getMessage()));
            ioe.printStackTrace();
            System.exit(1);
          }
        }     
    return hiddenTeamFilesFolder;
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

  public Collection<Team> getTeams() {
    return teams.values();
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
   *          the absolute file path
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

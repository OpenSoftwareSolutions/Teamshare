package com.oss.teamshare;

import java.nio.file.Path;
import java.util.ArrayList;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oss.teamshare.io.FilesystemWatcher;
import com.oss.teamshare.sync.Synchronization;
import com.oss.teamshare.team.DeviceId;
import com.oss.teamshare.team.Session;
import com.oss.teamshare.team.Team;
import com.oss.teamshare.team.TeamRepoException;
import com.oss.teamshare.team.UserId;

public class Main {
  
  public static Logger logger = LogManager.getLogger(Main.class);

  public Main() {
    
  }
  
  public static void main(String[] args) throws TeamRepoException {
    /* Read application input parameters from Java properties.*/
    String strUser = System.getProperty("teamshare.user");
    String strDevice = System.getProperty("teamshare.device");
    String strIcePort = System.getProperty("teamshare.ice.port");
    String strSwiftPort = System.getProperty("teamshare.swift.port");
    
    if (strUser == null || strDevice == null || strIcePort == null ||
        strSwiftPort == null) {
      throw new IllegalArgumentException(
          "You must provide properties teamshare.user, teamshare.device, teamshare.port.");
    }
    
    UserId userId = new UserId(strUser);
    DeviceId deviceId = new DeviceId(strDevice);
    logger.info(String.format("Starting Teamshare for user %s and device %s.",
        userId, deviceId));
    int icePort = Integer.parseInt(strIcePort);
    logger.info("Device server endpoint (Ice) listening on port " + icePort);
    
    /* Create session.*/
    try (Session session = Session.create(userId, deviceId, icePort)) {
      
      /* HACK: Send file event manually for testing.*/
//      Console console = System.console();
//      console.printf(
//          "Absolute path of the file for which to trigger a new file event: ");
//      String fileName = console.readLine();
//      Synchronization sync = new Synchronization(session);
//      sync.notifyFilesystemEvent(new FilesystemEvent(Paths.get(fileName),
//          FileEventType.CREATE));
      
      /* Start sync service.*/
      Synchronization sync = new Synchronization(session);
      
      /* Start FilesystemWatcher.*/
      FilesystemWatcher fsWatcher = null;
      fsWatcher = new FilesystemWatcher(
          session.getPath(), sync);
      
      // don't monitor changes in the team's hidden folder
      ArrayList<Path> excludedDirs = new ArrayList<Path>();
      for (Team team : session.getTeams()){
        excludedDirs.add(session.getHiddenTeamFolder(team));
      }
      
      fsWatcher.watch(true, excludedDirs);
      fsWatcher.start();

      System.out.println("Press any key to exit...");
      System.in.read();
      
      fsWatcher.stopWatching();
      fsWatcher.join();
      
      logger.info("Exiting Teamshare...");
    } catch (Exception e) {
      logger.fatal(ExceptionUtils.getStackTrace(e));
    }
  }

}

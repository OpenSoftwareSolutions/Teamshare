package com.oss.teamshare;


import java.io.Console;
import java.nio.file.Paths;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oss.teamshare.io.FileEventType;
import com.oss.teamshare.io.FilesystemEvent;
import com.oss.teamshare.io.FilesystemWatcher;
import com.oss.teamshare.sync.Synchronization;
import com.oss.teamshare.team.DeviceId;
import com.oss.teamshare.team.Session;
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
    String strPort = System.getProperty("teamshare.port");
    
    if (strUser == null || strDevice == null || strPort == null) {
      throw new IllegalArgumentException(
          "You must provide properties teamshare.user, teamshare.device, teamshare.port.");
    }
    
    UserId userId = new UserId(strUser);
    DeviceId deviceId = new DeviceId(strDevice);
    logger.info(String.format("Starting Teamshare for user %s and device %s.",
        userId, deviceId));
    int port = Integer.parseInt(strPort);
    logger.info("Device server endpoint listening on port " + port);
    
    /* Create session.*/
    try (Session session = new Session(userId, deviceId, port)) {
      
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
      fsWatcher.watch(true);
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

package com.oss.teamshare.sync;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oss.teamshare.communication.Swarm;
import com.oss.teamshare.communication.SwiftService;
import com.oss.teamshare.io.FileUtil;
import com.oss.teamshare.io.FilesystemEvent;
import com.oss.teamshare.io.TeamFile;
import com.oss.teamshare.team.*;


public class Synchronization {
  
  protected Session session;
  
  protected PushStrategy pushStrategy;
  protected PullStrategy pullStrategy;
  
  private Logger logger = LogManager.getLogger(this.getClass().getName());
  
  public Synchronization(Session session) {
    this.session = session;
    
    // TODO Initialize different strategies for different contexts.
    SwiftService swiftService = new SwiftService(); 
    this.pushStrategy = new SwiftPushStrategy(session, swiftService);
  }
  
  // XXX HACK!
  public void createSwarm(TeamFile file) {
    // Get swift port from property.
    String strSwiftPort = System.getProperty("teamshare.swift.port");
    int swiftPort = Integer.parseInt(strSwiftPort);
    logger.info("Swift listening on port " + swiftPort);
    
    // Find absolute file path.
    Path absFilePath = file.getAbsolutePath(session);
    
    // Create swarm by running swift in a thread.
    Swarm swarm = new Swarm(swiftPort, absFilePath);
    swarm.start();
    
    // Wait 10 second to give for the swarm to be created before the other
    // devices request content.
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {}
  }
 
  public void notifyFilesystemEvent(FilesystemEvent event) {
    logger.info(String.format(
        "File system event of type %s occured for file '%s'.", 
                    event.getEventType(), event.getFile()));
    
    TeamFile file = session.getTeamFile(event.getFile());
    logger.debug("Teamfile: " + file);
    
    // XXX HACK! Create a swift swarm.
    createSwarm(file);

    // TODO Cache file hash somewhere in a file
    byte[] hash;
    try {
      hash = computeFileHash(event.getFile());
    } catch (IOException e) {
      logger.error(String.format("Error while hashing physical file '%s': %s",
          event.getFile(), e.getMessage()));
      return;
    }
    
    pushStrategy.push(file);
  }
  
  public static byte[] computeFileHash(Path file) throws IOException {
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("SHA1");
    } catch (NoSuchAlgorithmException e) {
      throw new Error(e);
    }
    
    byte [] fileData = Files.readAllBytes(file); 
    md.update(fileData);
  
    
   // input.close();
    
    return md.digest();
  }
}

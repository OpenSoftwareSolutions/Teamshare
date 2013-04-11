package com.oss.teamshare.sync;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
 
  public void notifyFilesystemEvent(FilesystemEvent event) {
    logger.info(String.format(
        "File system event of type %s occured for file '%s'.", 
                    event.getEventType(), event.getFile()));
    
    TeamFile file = session.getTeamFile(event.getFile());
    logger.debug("Teamfile: " + file);

    // TODO Cache file hash somewhere in a file
    byte[] hash;
    try {
      hash = FileUtil.getFileHash(event.getFile());
    } catch (IOException e) {
      logger.error(String.format("Error while hashing physical file '%s': %s",
          event.getFile(), e.getMessage()));
      return;
    }
    
    pushStrategy.push(file);
  }
  
}

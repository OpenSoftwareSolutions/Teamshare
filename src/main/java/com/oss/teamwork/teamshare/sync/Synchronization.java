package com.oss.teamwork.teamshare.sync;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

import com.oss.teamwork.teamshare.common.Configuration;

import com.oss.teamwork.teamshare.io.FileUtil;
import com.oss.teamwork.teamshare.io.FilesystemEvent;
import com.oss.teamwork.teamshare.team.*;

public class Synchronization {
  
  protected Session session;
  
  // TODO Decide how strategies are loaded from Configuration.
  protected PushStrategy pushStrategy;
  protected PullStrategy pullStrategy;
  protected HashMap<Team, Change> changes;
  protected Timer pushScheduler;
  protected Timer pullScheduler;
  protected VersioningStrategy versioningStrategy;
  protected TeamRepo groupRepository;
  protected Session account;
  
  @SuppressWarnings("unused")
  private Logger logger = LogManager.getLogger(this.getClass().getName());
  
  public Synchronization(Session session) {
    this.session = session;
  }
 
  class PushTask extends TimerTask{

    @Override
    public void run() {
      for (Team group: changes.keySet()){
        Change change = changes.get(group);
        push(change);
        changes.put(group, new Change());
    }
    }
  }
  
  class PullTask extends TimerTask{

    @Override
    public void run() {
        // TODO Get logged in user teams.
        Collection<Team> groups = null; //account.getTeams();
        for (Team group: groups)
          pull(group);
    }
  }
  
  public Synchronization(){
    
    //TODO initialize strategies, account!
    
    //groupRepository = GroupRepositoryFactory.getInstance().getGroupRepository();
    
    pushScheduler = new Timer();
    pushScheduler.schedule(new PushTask(), 0, Configuration.getInstance().getPushInterval());
    
    pullScheduler = new Timer();
    pullScheduler.schedule(new PullTask(), 0, Configuration.getInstance().getPullInterval());
  }
  
  /**
   * Returns a reference to the team where the file is stored.
   * 
   * Pass a relative file name with Unix separator "/" where the first directory
   * identifies the team.
   * 
   * TODO UGLY PROTOTYPE! Change and move this somewhere else.
   * 
   * @param filename
   * @return
   */
  protected Team getTeamFromPhysicalFilename(String physicalFilename) {
    int firstSlash = physicalFilename.indexOf('/');
    
    return session.getTeam(new TeamId(physicalFilename.substring(0, firstSlash)));
  }
  
  /**
   * TODO UGLY PROTOTYPE! Change and move this somewhere else.
   * 
   * @param physicalFilename
   * @return
   */
  protected String getLogicalFilename(String physicalFilename) {
    int firstSlash = physicalFilename.indexOf('/');
    
    return physicalFilename.substring(firstSlash + 1);
  }
  
  public void notifyFilesystemEvent(FilesystemEvent event) {
    logger.info("File system event occured for file '" + event.getFilename() +
        "'.");
    String physicalFilename = event.getFilename();
    byte[] hash;
    
    try {
      hash = FileUtil.getFileHash(physicalFilename);
    } catch (IOException e) {
      logger.error(String.format("Error while hashing physical file '%s': %s",
          physicalFilename, e.getMessage()));
      return;
    }
    
    Revision revision = new Revision(
        getTeamFromPhysicalFilename(physicalFilename),
        getLogicalFilename(physicalFilename),
        hash, System.currentTimeMillis());
  }
  
  protected void push(Change change) {
   
    pushStrategy.push(change);
  }
  
  protected void pull(Team group) {
    
    Collection<Change> changes = pullStrategy.pull(group);   
    
    
  }
  
  protected void updateChange(FilesystemEvent event) {

    // TODO Get team by file.
    Team group = null; // groupRepository.getGroup(event.getFile());
    changes.get(group).update(event);
    
  }
  
  
}

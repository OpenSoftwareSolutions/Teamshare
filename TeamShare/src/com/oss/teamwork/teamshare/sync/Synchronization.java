package com.oss.teamwork.teamshare.sync;
import com.oss.teamwork.teamshare.group.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.oss.teamwork.teamshare.common.Configuration;

import com.oss.teamwork.teamshare.io.FilesystemEvent;

public class Synchronization {
  
  // TODO Decide how strategies are loaded from Configuration.
  protected PushStrategy pushStrategy;
  protected PullStrategy pullStrategy;
  protected HashMap<Group, Change> changes;
  protected Timer pushScheduler;
  protected Timer pullScheduler;
  protected VersioningStrategy versioningStrategy;
  protected GroupRepository groupRepository;
  protected Session account;
 
  class PushTask extends TimerTask{

    @Override
    public void run() {
      for (Group group: changes.keySet()){
        Change change = changes.get(group);
        if (!change.isEmpty()){
          push(change);
     
          changes.put(group, new Change());
        }
    }
    }
  }
  
  class PullTask extends TimerTask{

    @Override
    public void run() {
        Collection<Group> groups = account.getMyGroups();
        for (Group group: groups)
          pull(group);
    }
  }
  
  public Synchronization(){
    
    //TODO initialize strategies, account!
    
    groupRepository = GroupRepositoryFactory.getInstance().getGroupRepository();
    
    pushScheduler = new Timer();
    pushScheduler.schedule(new PushTask(), 0, Configuration.getInstance().getPushInterval());
    
    pullScheduler = new Timer();
    pullScheduler.schedule(new PullTask(), 0, Configuration.getInstance().getPullInterval());
  }
  
  public void notifyFilesystemEvent(FilesystemEvent event) {
    updateChange(event);
    
  }
  
  protected void push(Change change) {
   
    pushStrategy.push(change);
  }
  
  protected void pull(Group group) {
    
    Collection<Change> changes = pullStrategy.pull(group);   
    
    
  }
  
  protected void updateChange(FilesystemEvent event) {

    Group group = groupRepository.getGroup(event.getFile());
    changes.get(group).update(event);
    
  }
  
  
}

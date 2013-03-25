package com.oss.teamwork.teamshare.sync;

import org.joda.time.DateTime;

import com.oss.teamwork.teamshare.io.FilesystemEvent;
import com.oss.teamwork.teamshare.team.Team;

// TODO review Change class
/**
 * Change represents a file update.
 * 
 */
public class Change {

  Team team;
  
  String filename;
  
  Revision revision;
  
  /**
   * Device's local UTC time when the change was made.
   */
  DateTime timestamp;
  
  public void update(FilesystemEvent fsEvent) {
  }
}

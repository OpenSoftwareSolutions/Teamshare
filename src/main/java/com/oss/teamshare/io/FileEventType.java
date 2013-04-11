package com.oss.teamshare.io;

/**
 * Event types that can occur for files or folders.
 * @author adriana
 *
 */
public enum FileEventType{
  /**
   * Event triggered when a new file was created
   */
  CREATE,
  
  /**
   * Event triggered when a file was deleted
   */
  DELETE,
  
  /**
   * Event triggered when a new file was modified
   */
  MODIFY;
}

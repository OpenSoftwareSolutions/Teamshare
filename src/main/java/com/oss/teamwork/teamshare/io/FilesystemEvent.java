package com.oss.teamwork.teamshare.io;

import org.joda.time.DateTime;


public class FilesystemEvent {
  
  // TODO Do we need an event interface?
  
  private DateTime time;
  private java.io.File file; // TODO or our file?
  
  public DateTime getTime() {
    return time;
  }
  public void setTime(DateTime time) {
    this.time = time;
  }
  public java.io.File getFile() {
    return file;
  }
  public void setFile(java.io.File file) {
    this.file = file;
  }

}

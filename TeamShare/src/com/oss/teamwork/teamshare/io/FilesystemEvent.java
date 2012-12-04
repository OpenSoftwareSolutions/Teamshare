package com.oss.teamwork.teamshare.io;

import java.util.Date;

public class FilesystemEvent {
  
  // TODO Do we need an event interface?
  
  private Date time;
  private java.io.File file; // TODO or our file?
  
  public Date getTime() {
    return time;
  }
  public void setTime(Date time) {
    this.time = time;
  }
  public java.io.File getFile() {
    return file;
  }
  public void setFile(java.io.File file) {
    this.file = file;
  }

}

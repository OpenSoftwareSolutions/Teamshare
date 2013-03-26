package com.oss.teamshare.io;


public class FilesystemEvent {
  
  protected String filename;
  
  public FilesystemEvent(String filename) {
    super();
    this.filename = filename;
  }
  
  public String getFilename() {
    return filename;
  }
}

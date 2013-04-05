package com.oss.teamshare.io;


public class FilesystemEvent {
  
  protected java.io.File file;

  public FilesystemEvent(java.io.File file) {
    super();
    this.file = file;
  }

  public java.io.File getFile() {
    return file;
  }
}

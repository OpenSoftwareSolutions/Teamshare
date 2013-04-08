package com.oss.teamshare.io;

import java.nio.file.Path;


public class FilesystemEvent {
  
  protected Path file;

  public FilesystemEvent(Path file) {
    super();
    this.file = file;
  }

  public Path getFile() {
    return file;
  }
}

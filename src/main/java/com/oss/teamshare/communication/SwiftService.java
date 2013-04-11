package com.oss.teamshare.communication;

import java.nio.file.Path;

public class SwiftService {

  public SwiftService() {

  }
  
  /**
   * Starts seeding for a file and return its swarm ID.
   * 
   * @param file the file to start seeding for
   * @return swarm ID hash value which identifies content
   */
  public byte[] seed(Path file) {
    // FIXME Return a real swarm ID.
    return new byte[20];
  }
  
  public void download(byte[] swarmId) {
    
  }
}

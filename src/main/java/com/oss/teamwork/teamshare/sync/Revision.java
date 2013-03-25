/**
 * 
 */
package com.oss.teamwork.teamshare.sync;

import com.oss.teamwork.teamshare.team.Team;

public class Revision {
  
  public Revision(Team team, String filename, byte[] hash, long time) {
    super();
    this.team = team;
    this.filename = filename;
    this.hash = hash;
    this.time = time;
  }
  
  protected Team team;
  protected String filename;
  protected byte[] hash;
  protected long time;
  
  public Team getTeam() {
    return team;
  }
  
  public String getFilename() {
    return filename;
  }
  
  public byte[] getHash() {
    return hash;
  }
  
  public long getTime() {
    return time;
  }
  
  
}

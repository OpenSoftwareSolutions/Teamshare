package com.oss.teamshare.io;

import java.io.File;

import com.oss.teamshare.team.Team;

public class TeamFile extends File {

  private Team team;
  
  private static final long serialVersionUID = -604163701302093930L;

  public TeamFile(Team team, String pathname) {
    super(pathname);
    
    this.team = team;
  }
  
  // TODO createFromAbsoluteFile
  public static TeamFile createFromAbsoluteFile(File absoluteFile) {
    return null;
  }

  public Team getTeam() {
    return team;
  }

  // TODO getAbsoluteFile
  public File getAbsoluteFile() {
    return null;
  }
}

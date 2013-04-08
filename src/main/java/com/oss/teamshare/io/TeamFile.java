package com.oss.teamshare.io;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;


import com.oss.teamshare.team.Session;
import com.oss.teamshare.team.Team;
import com.oss.teamshare.team.TeamId;

/**
 * Represents a file from a team by storing file's relative path to its team
 * path and a reference to its team object.
 * 
 * This class instances should be immutable value objects.
 * 
 * @author calinburloiu
 */
public class TeamFile {

  private Team team;
  private Path path;
  
  public static final String URI_SCHEME = "teamshare";

  public TeamFile(Team team, Path path) {
    this.team = team;
    this.path = path;
  }
  
  public TeamFile(String uriString, Session session) {
    // Check and escape URI scheme.
    String scheme = URI_SCHEME + "://";
    if (uriString.indexOf(scheme) != 0) {
      throw new IllegalArgumentException("Missing scheme");
    }
    String withoutScheme = uriString.substring(scheme.length());
    
    // Team
    int slash = withoutScheme.indexOf('/');
    if (slash == -1) {
      throw new IllegalArgumentException("Missing team ID");
    }
    TeamId teamId = new TeamId(withoutScheme.substring(0, slash));
    team = session.getTeam(teamId);
    
    // File path
    String uriPath = withoutScheme.substring(slash + 1);
    if (uriPath.length() == 0) {
      throw new IllegalArgumentException("Missing file path");
    }
    path = Paths.get(uriPath.replace('/',
        FileSystems.getDefault().getSeparator().charAt(0)));
  }

  public Team getTeam() {
    return team;
  }

  public Path getPath() {
    return path;
  }

  public String toUriString() {
    String uriPath = path.toString().replace(
        FileSystems.getDefault().getSeparator().charAt(0), '/');
    
    return String.format("teamshare://%s/%s", team.getId().toString(), uriPath);
  }
  
  public Path getAbsolutePath(Session session) {
    return session.getPath().resolve(team.getPath()).resolve(path);
  }
}

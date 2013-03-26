package com.oss.teamshare;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oss.teamshare.team.JsonTeamRepo;
import com.oss.teamshare.team.Team;
import com.oss.teamshare.team.TeamId;
import com.oss.teamshare.team.TeamRepo;
import com.oss.teamshare.team.TeamRepoException;
import com.oss.teamshare.team.UserId;

public class Main {
  
  public static Logger logger = LogManager.getLogger("Teamshare");

  public Main() {
    
  }
  
  public static void main(String[] args) throws TeamRepoException {
    TeamRepo teamRepo = new JsonTeamRepo();
    Map<TeamId, Team> teams = teamRepo.retrieveUserTeams(new UserId("idCalin"));
    
    for (Team team : teams.values()) {
      System.out.println(team);
    }
  }

}

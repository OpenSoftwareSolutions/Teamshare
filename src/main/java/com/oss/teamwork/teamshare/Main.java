package com.oss.teamwork.teamshare;

import java.util.Map;

import com.oss.teamwork.teamshare.team.JsonTeamRepo;
import com.oss.teamwork.teamshare.team.Team;
import com.oss.teamwork.teamshare.team.TeamId;
import com.oss.teamwork.teamshare.team.TeamRepo;
import com.oss.teamwork.teamshare.team.TeamRepoException;
import com.oss.teamwork.teamshare.team.UserId;

public class Main {

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

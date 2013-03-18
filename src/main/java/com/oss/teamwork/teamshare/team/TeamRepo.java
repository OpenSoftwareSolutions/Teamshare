package com.oss.teamwork.teamshare.team;

import java.util.Map;

public interface TeamRepo {
  
  Map<TeamId, Team> retrieveUserTeams(UserId userId) throws TeamRepoException;
}

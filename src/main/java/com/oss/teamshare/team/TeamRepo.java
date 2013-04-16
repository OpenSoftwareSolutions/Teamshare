package com.oss.teamshare.team;

import java.util.Map;

public interface TeamRepo {
  
  Map<TeamId, Team> retrieveUserTeams(UserId userId, DeviceId deviceId)
      throws TeamRepoException;
}

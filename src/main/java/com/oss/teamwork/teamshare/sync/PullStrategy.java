package com.oss.teamwork.teamshare.sync;

import java.util.Collection;

import com.oss.teamwork.teamshare.team.Team;

public interface PullStrategy {
  
  Collection<Change> pull(Team group);
}

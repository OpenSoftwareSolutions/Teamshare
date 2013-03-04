package com.oss.teamwork.teamshare.sync;

import java.util.Collection;

import com.oss.teamwork.teamshare.group.Group;

public interface PullStrategy {
  
  Collection<Change> pull(Group group);
}

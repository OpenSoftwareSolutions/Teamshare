package com.oss.teamshare.sync;

import java.util.Collection;

//TODO
public interface ChangeHistory {

  void addChange(Change change);
  void addChanges(Collection<Change> changes);
  Collection<Change> getChanges(ChangesQuery criteria);
}

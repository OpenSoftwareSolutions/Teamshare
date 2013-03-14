package com.oss.teamwork.teamshare.common;

public class TeamId extends Id {

  protected TeamId() {}
  
  protected TeamId(byte[] hash) {
    super(hash);
  }
  
  // TODO create group ID based on some criteria
  public static TeamId createGroupId() {
    return new TeamId(null);
  }
  
}

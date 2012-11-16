package com.oss.teamwork.teamshare.common;

public class GroupId extends Id {

  protected GroupId() {}
  
  protected GroupId(byte[] hash) {
    super(hash);
  }
  
  // TODO create group ID based on some criteria
  public static GroupId createGroupId() {
    return new GroupId(null);
  }
  
}

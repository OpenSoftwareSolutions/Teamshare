package com.oss.teamwork.teamshare.common;


public class UserId extends Id{
 protected UserId() {}
  
  protected UserId(byte[] hash) {
    super(hash);
  }
  
  // TODO create group ID based on some criteria
  public static UserId createGroupId() {
    return new UserId(null);
  }
}

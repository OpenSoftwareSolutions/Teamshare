package org.oss.teamwork.teamshare.common;

public class ChangeId extends Id {

  protected ChangeId() {}
  
  protected ChangeId(byte[] hash) {
    super(hash);
  }
  
  // TODO create change ID based on some criteria
  public static ChangeId createChangeId() {
    return new ChangeId(null);
  }
}

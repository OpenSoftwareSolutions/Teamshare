package com.oss.teamwork.teamshare.messaging;

public interface AcceptableMessage {

  public static enum Type {
    REQUEST, ACCEPT, REFUSE
  }

  /**
   * Send an acceptance reply to this message.
   */
  public abstract void accept();

  /**
   * Send a refusal reply to this message.
   */
  public abstract void refuse();
}

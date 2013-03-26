package com.oss.teamshare.messaging;

public interface AcceptableMessage {

  static enum Type {
    REQUEST, ACCEPT, REFUSE
  }

  /**
   * Send an acceptance reply to this message.
   */
  abstract void accept();

  /**
   * Send a refusal reply to this message.
   */
  abstract void refuse();
}

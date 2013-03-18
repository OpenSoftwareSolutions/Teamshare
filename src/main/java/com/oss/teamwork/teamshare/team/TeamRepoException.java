package com.oss.teamwork.teamshare.team;

public class TeamRepoException extends Exception {

  private static final long serialVersionUID = -6603666943765172330L;

  public TeamRepoException() {
  }

  public TeamRepoException(String message) {
    super(message);
  }

  public TeamRepoException(Throwable cause) {
    super(cause);
  }

  public TeamRepoException(String message, Throwable cause) {
    super(message, cause);
  }

  public TeamRepoException(String message, Throwable cause,
      boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}

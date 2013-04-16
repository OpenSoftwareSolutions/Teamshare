package com.oss.teamshare.team;

public class Person {

  /**
   * The user's email address, used for receiving notifications. Two users
   * cannot have the same email address.
   */
  protected String email;

  public Person() {
    super();
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
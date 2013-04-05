package com.oss.teamshare.common;

public class Configuration {
  private static Configuration instance;
  
  /**
   * Teamshare folder path mounted after the user authenticates.\
   * 
   * Defaults to '$HOME/Teamshare'.
   */
  private String path;

  private long pushInterval = 10000;
  private long pullInterval = 120000;
  
  private Configuration(){
    path = System.getProperty("user.home");
  }
  
  public static Configuration getInstance() {
    if (instance == null) 
      instance = new Configuration();
    return instance;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public long getPushInterval() {
    return pushInterval;
  }

  public void setPushInterval(long pushInterval) {
    this.pushInterval = pushInterval;
  }
  
  public long getPullInterval() {
   
    return pullInterval;
  }

  public void setPullInterval(long pullInterval) {
    this.pullInterval = pullInterval;
  }
}

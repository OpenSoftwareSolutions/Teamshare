package com.oss.teamshare.common;

public class Configuration {
  private static Configuration instance;
  
  private long pushInterval = 10000;
  private long pullInterval = 120000;
  
  private Configuration(){
   
  }
  
  public static Configuration getInstance() {
    if (instance == null) 
      instance = new Configuration();
    return instance;
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

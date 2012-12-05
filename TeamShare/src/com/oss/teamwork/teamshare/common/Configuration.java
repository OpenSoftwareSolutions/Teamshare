package com.oss.teamwork.teamshare.common;

import java.io.File;

public class Configuration {
  private long pushInterval = 10000;
  private long pullInterval = 100000;
  
  private Configuration(){
   
  }
  
  public static Configuration getInstance() {
    if (INSTANCE == null) 
      INSTANCE = new Configuration();
    return INSTANCE;
  }
  
  
  public long getPushInterval() {
    return pushInterval;
  }

  public void setPushInterval(long pushInterval) {
    this.pushInterval = pushInterval;
  }
  
  public void loadConfiguration(File confFile){}
  
  private static Configuration INSTANCE;

  public long getPullInterval() {
   
    return pullInterval;
  }
 
  
  
}

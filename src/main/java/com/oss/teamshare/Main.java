package com.oss.teamshare;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oss.teamshare.team.DeviceId;
import com.oss.teamshare.team.Session;
import com.oss.teamshare.team.TeamRepoException;
import com.oss.teamshare.team.UserId;

public class Main {
  
  public static Logger logger = LogManager.getLogger(Main.class);

  public Main() {
    
  }
  
  public static void main(String[] args) throws TeamRepoException {
    String strUser = System.getProperty("teamshare.user");
    String strDevice = System.getProperty("teamshare.device");
    String strPort = System.getProperty("teamshare.port");
    
    if (strUser == null || strDevice == null || strPort == null) {
      throw new IllegalArgumentException(
          "You must provide properties teamshare.user, teamshare.device, teamshare.port.");
    }
    
    UserId userId = new UserId(strUser);
    DeviceId deviceId = new DeviceId(strDevice);
    logger.info(String.format("Starting Teamshare for user %s and device %s.",
        userId, deviceId));
    int port = Integer.parseInt(strPort);
    logger.info("Device server endpoint listening on port " + port);
    
    /* Create session.*/
    try (Session session = new Session(userId, deviceId, port)) {
      Thread.sleep(10000);
      logger.info("Exiting Teamshare...");
    } catch (Exception e) {
      logger.fatal(ExceptionUtils.getStackTrace(e));
    }
  }

}

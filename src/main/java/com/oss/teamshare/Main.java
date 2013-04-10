package com.oss.teamshare;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oss.teamshare.team.DeviceId;
import com.oss.teamshare.team.JsonTeamRepo;
import com.oss.teamshare.team.Session;
import com.oss.teamshare.team.Team;
import com.oss.teamshare.team.TeamId;
import com.oss.teamshare.team.TeamRepo;
import com.oss.teamshare.team.TeamRepoException;
import com.oss.teamshare.team.UserId;

public class Main {
  
  public static Logger logger = LogManager.getLogger(Main.class);

  public Main() {
    
  }
  
  public static void main(String[] args) throws TeamRepoException {
    logger.info("Starting Teamshare...");
    
    /* Parse arguments.*/
    if (args.length < 2) {
      logger.fatal("usage: ${TEAMSHARE} userId deviceId");
      System.exit(1);
    }
    UserId userId = new UserId(args[0]);
    DeviceId deviceId = new DeviceId(args[1]);
    
    /* Create session.*/
    Session session = new Session(userId, deviceId);
    
    logger.info("Exiting Teamshare...");
  }

}

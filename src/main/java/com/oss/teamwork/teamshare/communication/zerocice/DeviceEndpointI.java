package com.oss.teamwork.teamshare.communication.zerocice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Ice.Current;

public class DeviceEndpointI extends _DeviceEndpointDisp {
  
  private Logger logger = LogManager.getLogger("DeviceEndpointI");

  private static final long serialVersionUID = -3537283385423250006L;

  @Override
  public void notifyRevision(String teamId, String filename, String swarmId,
      Current __current) {
    logger.debug(teamId + ", " + filename + ", " + swarmId);
    System.err.println(teamId + ", " + filename + ", " + swarmId);

  }

  @Override
  public String getLatestRevisionHash(String teamId, String filename,
      Current __current) {
    // TODO Auto-generated method stub
    return null;
  }

}

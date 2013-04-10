package com.oss.teamshare.communication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oss.teamshare.communication.zerocice._DeviceEndpointDisp;

import Ice.Current;

public class DeviceEndpointI extends _DeviceEndpointDisp {
  
  private Logger logger = LogManager.getLogger("DeviceEndpointI");

  private static final long serialVersionUID = -3537283385423250006L;

  @Override
  public void notifyRevision(String uri, String swarmId,
      Current __current) {
    logger.debug(uri + ", " + swarmId);

  }

}

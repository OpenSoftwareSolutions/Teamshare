package com.oss.teamshare.communication;

import java.net.InetSocketAddress;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oss.teamshare.communication.zerocice._DeviceEndpointDisp;
import com.oss.teamshare.io.TeamFile;
import com.oss.teamshare.team.Session;

import Ice.Current;

public class DeviceEndpointI extends _DeviceEndpointDisp {
  
  private Logger logger = LogManager.getLogger("DeviceEndpointI");

  private static final long serialVersionUID = -3537283385423250006L;

  @Override
  public void notifyRevision(String uri, String swarmId,
      Current __current) {
    logger.info(String.format("A notification was received for a new " +
        "revision of %s available in swarm ID %s.", uri, swarmId));
    
    Session session = Session.getInstance();
    TeamFile file = new TeamFile(uri, session);
    Path absFilePath = file.getAbsolutePath(session);
    
    // XXX HACK! Download a single file with a hacked swift Java wrapper.
    
  }

}

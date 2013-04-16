package com.oss.teamshare.communication;

import java.net.InetSocketAddress;
import java.nio.file.Path;

import javax.xml.bind.DatatypeConverter;

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
  public void notifyRevision(String uri, String swarmId, String seederAddress,
      Current __current) {
    logger.info(String.format("A notification was received for a new " +
        "revision of %s available in swarm ID %s.", uri, swarmId));
    
    // Compute file absolute path.
    Session session = Session.getInstance();
    TeamFile file = new TeamFile(uri, session);
    Path absFilePath = file.getAbsolutePath(session);
    
    // Compute swarm ID as byte array.
    byte[] byteSwarmId = DatatypeConverter.parseHexBinary(swarmId);
    
    // Compute seeder address.
    InetSocketAddress seederIsa = null;
    if (seederAddress.indexOf(':') == -1) {
      int port = Integer.parseInt(seederAddress);
      seederIsa = new InetSocketAddress("localhost", port);
    } else {
      String[] parts = seederAddress.split(":");
      String hostname = parts[0];
      int port = Integer.parseInt(parts[1]);
      seederIsa = new InetSocketAddress(hostname, port);
    }
    
    // Get local listening port for swift.
    int port = Swarm.getPortFromProperty();
    
    // XXX HACK! Download a single file with a hacked swift Java wrapper.
    logger.info(String.format("Starting swift in leeching mode in order to " +
        "download a new file from (%s, %s) to '%s'.", swarmId,
        seederIsa.toString(), absFilePath));
    Swarm swarm = new Swarm(port, absFilePath, seederIsa, byteSwarmId);
    swarm.start();
  }

}

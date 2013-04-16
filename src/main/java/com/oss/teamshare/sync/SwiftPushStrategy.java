package com.oss.teamshare.sync;

import java.nio.file.Path;
import java.util.Collection;

import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oss.teamshare.communication.Swarm;
import com.oss.teamshare.communication.SwiftService;
import com.oss.teamshare.communication.zerocice.DeviceEndpointPrx;
import com.oss.teamshare.io.TeamFile;
import com.oss.teamshare.team.Device;
import com.oss.teamshare.team.Session;
import com.oss.teamshare.team.Team;

public class SwiftPushStrategy implements PushStrategy {

  private Session session;
  private SwiftService swiftService;
  
  private static Logger logger = LogManager.getLogger(SwiftPushStrategy.class);

  public SwiftPushStrategy(Session session, SwiftService swiftService) {
    super();
    this.session = session;
    this.swiftService = swiftService;
  }
  
  // XXX HACK!
  public byte[] seed(TeamFile file) {
    // Get swift port from property.
    int swiftPort = Swarm.getPortFromProperty();
    logger.info("Swift listening on port " + swiftPort);
    
    // Find absolute file path.
    Path absFilePath = file.getAbsolutePath(session);
    
    // Create swarm by running swift in a thread.
    logger.info("Starting swift in seeding mode...");
    Swarm swarm = new Swarm(swiftPort, absFilePath);
    byte[] swarmId = swarm.getSwarmId();
    logger.info("Swarm ID is " +
        DatatypeConverter.printHexBinary(swarmId).toLowerCase());
    swarm.start();
    
    // Wait to give time to the swarm to be created before the other
    // devices request content.
    try {
      logger.debug("Waiting...");
      Thread.sleep(5000);
    } catch (InterruptedException e) {}
    
    return swarmId;
  }

  public void push(TeamFile file) {
    // XXX HACK! Create a new swarm for the new revision.
    byte[] swarmId = seed(file);
    
    /* Get online devices.*/
    Team team = file.getTeam();
    Collection<Device> onlineDevices = team.getOnlineDevices();
    logger.debug("onlineDevices = " + onlineDevices);
    
    String uri = file.toUriString();
    String strSwarmId = DatatypeConverter.printHexBinary(swarmId).toLowerCase();
    
    /* Notify all online devices of the change.*/
    for (Device dev : onlineDevices) {
      logger.info(String.format(
          "Notifying device %s that a revision of %s can be downloaded from " +
          "swarm ID %s.", dev.getId(), uri, strSwarmId));
      DeviceEndpointPrx endpoint = dev.getEndpoint();
      endpoint.notifyRevision(uri, strSwarmId, dev.getAddress().getHostAddress()
          + ":" + dev.getSwiftPort());
    }
  }

}

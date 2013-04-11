package com.oss.teamshare.sync;

import java.util.Collection;

import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

  public void push(TeamFile file) {
    /* Create a new swarm for the new revision.*/
    byte[] swarmId = swiftService.seed(file.getAbsolutePath(session));
    
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
      endpoint.notifyRevision(uri, strSwarmId);
    }
  }

}

package com.oss.teamshare.sync;

import java.util.Collection;

import javax.xml.bind.DatatypeConverter;

import com.oss.teamshare.communication.SwiftService;
import com.oss.teamshare.communication.zerocice.DeviceEndpointPrx;
import com.oss.teamshare.io.TeamFile;
import com.oss.teamshare.team.Device;
import com.oss.teamshare.team.Session;
import com.oss.teamshare.team.Team;

public class SwiftPushStrategy implements PushStrategy {

  private SwiftService swiftService;
  private Session session;
  
  public SwiftPushStrategy() {
    swiftService = new SwiftService();
  }

  public void push(TeamFile file) {
    // Create a new swarm for the new revision.
    byte[] swarmId = swiftService.seed(file.getAbsolutePath(session));
    
    // Get online devices.
    Team team = file.getTeam();
    Collection<Device> onlineDevices = team.getOnlineDevices();
    
    String uri = file.toUriString();
    String strSwarmId = DatatypeConverter.printHexBinary(swarmId).toLowerCase();
    
    for (Device dev : onlineDevices) {
      DeviceEndpointPrx endpoint = dev.getEndpoint();
      endpoint.notifyRevision(uri, strSwarmId);
    }
  }

}

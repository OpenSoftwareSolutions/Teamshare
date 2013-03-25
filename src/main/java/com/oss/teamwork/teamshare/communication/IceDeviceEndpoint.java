package com.oss.teamwork.teamshare.communication;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oss.teamwork.teamshare.communication.zerocice.DeviceEndpointPrx;
import com.oss.teamwork.teamshare.communication.zerocice.DeviceEndpointPrxHelper;
import com.oss.teamwork.teamshare.sync.Revision;
import com.oss.teamwork.teamshare.team.DeviceId;

public class IceDeviceEndpoint implements DeviceEndpoint {
  
  protected Ice.Communicator ic;
  protected DeviceEndpointPrx proxy;
  
  private Logger logger = null;

  public IceDeviceEndpoint(DeviceId id, int port) {
    logger = LogManager.getLogger("DeviceEndpoint-" + id);
    logger.debug("logger initialized");
    
    try {
      ic = Ice.Util.initialize();
      Ice.ObjectPrx base = ic.stringToProxy("dev-" + id + ":default -p "
          + port);
      proxy = DeviceEndpointPrxHelper.checkedCast(base);
      if (proxy == null) {
        throw new Error("Invalid proxy.");
      }
      
      
    } catch (Ice.LocalException e) {
      logger.error("An error occurred while initializing DeviceEndpoint: "
          + e.getMessage());
      try {
        close();
      } catch (IOException e1) {}
      System.exit(1);
    }
  }

  @Override
  public void notifyRevision(Revision revision, byte[] swarmId) {
    proxy.notifyRevision("TEAM_ID", "cale/catre/fisier", "SWARM_ID");
  }

  @Override
  public void close() throws IOException {
    if (ic == null) {
      try {
        ic.destroy();
      } catch (Ice.LocalException e) {
        logger.error("An error occurred while destroying DeviceEndpoint: "
            + e.getMessage());
      }
    }
  }
  
  public static void main(String[] args) throws IOException {
    DeviceEndpoint dev = new IceDeviceEndpoint(new DeviceId("cucu"), 6881);
    System.err.println("client started");
    dev.notifyRevision(null, null);
    System.err.println("method called");
    dev.close();
    System.err.println("client closed");
    System.exit(0);
  }

}

package com.oss.teamwork.teamshare.communication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oss.teamwork.teamshare.communication.zerocice.DeviceEndpointI;
import com.oss.teamwork.teamshare.team.DeviceId;

public class DeviceServer extends Thread {

  protected DeviceId id;
  protected int port;
  
  private Logger logger = LogManager.getLogger("DeviceServer");
  
  public DeviceServer(DeviceId id, int port) {
    this.id = id;
    this.port = port;
  }
  
  public void run() {
    Ice.Communicator ic = null;
    int status = 0;
    
    try {
      ic = Ice.Util.initialize();
      Ice.ObjectAdapter adapter = 
          ic.createObjectAdapterWithEndpoints("devAdapter",
              "default -p " + port);
      Ice.Object object = new DeviceEndpointI();
      adapter.add(object, ic.stringToIdentity("dev-" + id));
      adapter.activate();
      
      logger.info("DeviceServer started.");
      ic.waitForShutdown();
    } catch (Ice.LocalException e) {
      logger.fatal("An error occurred while initializing DeviceServer: "
          + e.getMessage());
      status = 1;
    }
    
    if (ic != null) {
      // Clean up.
      try {
        ic.destroy();
      } catch (Exception e) {
        logger.error("An error occurred while destroying DeviceServer: "
            + e.getMessage());
      }
    }
    
    // Exit if a fatal error occured.
    if (status > 0) {
      System.exit(status);
    }
  }
  
  public static void main(String[] args) {
    DeviceServer server = new DeviceServer(new DeviceId("cucu"), 6881);
    server.start();
  }

}

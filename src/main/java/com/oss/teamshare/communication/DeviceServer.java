package com.oss.teamshare.communication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oss.teamshare.team.DeviceId;

public class DeviceServer extends Thread {

  protected DeviceId id;
  protected int port;
  
  private Logger logger = LogManager.getLogger(DeviceServer.class);
  
  public DeviceServer(DeviceId id, int port) {
    this.id = id;
    this.port = port;
  }
  
  public void run() {
    Ice.Communicator ic = IceRuntime.getInstance().getCommunicator();
//    Ice.Communicator ic = Ice.Util.initialize();
    int status = 0;
    
    try {
      Ice.ObjectAdapter adapter = 
          ic.createObjectAdapterWithEndpoints("DeviceServerAdapter",
              "default -p " + port);
      Ice.Object object = new DeviceEndpointI();
      adapter.add(object, ic.stringToIdentity(id.toString()));
      logger.debug("device ID: " + id);
      adapter.activate();
      
      logger.info("DeviceServer started.");
      ic.waitForShutdown();
    } catch (Ice.LocalException e) {
      logger.fatal("Failed to initialize DeviceServer: "
          + e.getMessage());
      status = 1;
    }
    
    if (ic != null) {
      // Clean up.
      try {
        ic.destroy();
      } catch (Exception e) {
        logger.error("Failed to destroy DeviceServer: "
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

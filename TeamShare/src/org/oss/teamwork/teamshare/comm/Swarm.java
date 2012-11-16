package org.oss.teamwork.teamshare.comm;

import java.io.InputStream;
import java.util.Set;

import org.oss.teamwork.teamshare.common.Id;
import org.oss.teamwork.teamshare.group.Device;

public interface Swarm {

  /*  ______________________________________________________________________
   * | GETTERS                                                              |
   * |______________________________________________________________________|
  */
  
  /**
   * Return ID of content shared by the swarm. 
   */
  Id getSwarmId();
  
  /**
   * Return whether my device is a seeder (it finished download) in this swarm.
   */
  boolean isSeeding();
  
  /**
   * Return the set of devices (peers) from the swarm.
   */
  Set<Device> getDevices();
  
  /**
   * Return an InputStream for reading the content shared by the swarm.
   */
  InputStream getInputStream();
  
  
  /*  ______________________________________________________________________
   * | OPERATIONS                                                           |
   * |______________________________________________________________________|
  */

  /** 
   * Start / resume download and upload of content from / to the swarm.
   */
  void start();
  
  /** 
   * Stop download and upload of content from / to the swarm.
   */
  void stop();
}

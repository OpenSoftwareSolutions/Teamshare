package com.oss.teamshare.communication;

import java.net.InetSocketAddress;
import java.nio.file.Path;

import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oss.teamshare.communication.swift.swift;

public class Swarm extends Thread {

  private static Logger logger = LogManager.getLogger(Swarm.class);
  
  static {
    try {
      System.loadLibrary("swift");
    } catch (UnsatisfiedLinkError e) {
      logger.fatal("Failed to load native code library \"swift\".");
    }
  }
  
  /**
   * Listen port
   */
  private int port;
  
  /**
   * Path of the file which is shared with swift
   */
  private Path filePath;
  
  /**
   * Swift seeder address to connect for leeching mode. Has null value in
   * seeding mode.
   */
  private InetSocketAddress seederAddress;

  /**
   * ID of content shared by the swarm, necessary for leeching mode. Has null
   * value in seeding mode. 
   */
  private byte[] swarmId;
  
  /**
   * Construct a swarm for leeching mode.
   * 
   * @param port
   * @param filePath
   * @param seederAddress
   * @param swarmId
   */
  public Swarm(int port, Path filePath, InetSocketAddress seederAddress,
      byte[] swarmId) {
    super();
    this.port = port;
    this.filePath = filePath;
    this.seederAddress = seederAddress;
    this.swarmId = swarmId;
  }
  
  /**
   * Construct a swarm for seeding mode.
   * 
   * @param port
   * @param filePath
   */
  public Swarm(int port, Path filePath) {
    this(port, filePath, null, null);
  }

  /**
   * Return whether my device is a seeder (it finished download) in this swarm.
   */
//  boolean isSeeding() {
//    // TODO isSeeding
//    return true;
//  }
  
  @Override
  public void run() {
    super.run();
    
    String strPort = "" + port;
    String strFilePath = filePath.toString();
    String strSeederAddress = null;
    if (seederAddress != null) {
      strSeederAddress = seederAddress.getAddress().getHostAddress() +
          ":" + seederAddress.getPort();
    }
    String strSwarmId = null;
    if (swarmId != null) {
      strSwarmId = DatatypeConverter.printHexBinary(swarmId).toLowerCase();
    }
  
    swift.Start(strPort, strFilePath, strSeederAddress, strSwarmId);
  }
  
  public byte[] getSwarmId() {
    String strFilePath = filePath.toString();
    
    String strSwarmId = swift.GetRootHash(strFilePath);
    
    return DatatypeConverter.parseHexBinary(strSwarmId);
  }
  
  public static int getPortFromProperty() {
    String strSwiftPort = System.getProperty("teamshare.swift.port");
    int swiftPort = Integer.parseInt(strSwiftPort);
    
    return swiftPort;
  }
}

package com.oss.teamshare.sync;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oss.teamshare.communication.SwiftService;

import com.oss.teamshare.io.FilesystemEvent;
import com.oss.teamshare.io.TeamFile;
import com.oss.teamshare.team.*;


public class Synchronization {

  protected Session session;

  protected PushStrategy pushStrategy;
  protected PullStrategy pullStrategy;

  private Logger logger = LogManager.getLogger(this.getClass().getName());

  public Synchronization(Session session) {
    this.session = session;

    // TODO Initialize different strategies for different contexts.
    SwiftService swiftService = new SwiftService(); 
    this.pushStrategy = new SwiftPushStrategy(session, swiftService);
  }

  public void notifyFilesystemEvent(FilesystemEvent event) {
    logger.info(String.format(
        "File system event of type %s occured for file '%s'.", 
        event.getEventType(), event.getFile()));
    Path filePath = event.getFile(); // absolute file path

    TeamFile file = session.getTeamFile(filePath);
    logger.debug("Teamfile: " + file);

   
    try { 
      //TODO TEST ME
      hashFile(file, filePath);

    } catch (IOException e) {
      logger.error(String.format("Error while hashing physical file '%s': %s",
          filePath, e.getMessage()));
      return;
    }

    pushStrategy.push(file);
  }

  private void hashFile(TeamFile file, Path filePath) throws IOException {
    //TODO change hardcode ".team"
    String hidden = session.getPath().toString() + FileSystems.getDefault().getSeparator().charAt(0) 
        +".team"+ FileSystems.getDefault().getSeparator().charAt(0) 
        +file.getPath();
    Path hiddenPath = FileSystems.getDefault().getPath(hidden);

    if (Files.isDirectory(filePath)) {
      Files.createDirectory(hiddenPath);
      logger.debug(String.format("Created hidden entry for folder %s at %s", file.getPath(), hiddenPath));
    }
    else {
      byte[] hash = computeFileHash(filePath);
      logger.debug(String.format("Created hash for file %s", file));            

      Files.write(hiddenPath, hash, StandardOpenOption.CREATE, StandardOpenOption.WRITE);

      logger.debug(String.format("Stored hash for file %s at %s", file.getPath(), hiddenPath));
    }
  }

  public static byte[] computeFileHash(Path file) throws IOException {
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("SHA1");
    } catch (NoSuchAlgorithmException e) {
      throw new Error(e);
    }

    if (Files.size(file) < 1000000) { // small file size < ~1MB
      byte [] fileData = Files.readAllBytes(file); 
      md.update(fileData);
    }

    else {
      FileInputStream input = (FileInputStream)Files.newInputStream(file, StandardOpenOption.READ);
      byte[] fileData = new byte[4096];
      int count;
      while ((count = input.read(fileData)) != -1) {
        md.update(fileData, 0, count);
      }
    }

    return md.digest();
  }
}

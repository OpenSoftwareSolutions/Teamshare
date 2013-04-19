package com.oss.teamshare.sync;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oss.teamshare.communication.SwiftService;

import com.oss.teamshare.io.FileEventType;
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
     
      if (event.getEventType() != FileEventType.DELETE)
        hashFile(file, filePath);

    } catch (IOException ioe) {
      logger.error(String.format("Error while hashing physical file '%s': %s",
          filePath, ioe.getMessage()));
      ioe.printStackTrace();
      return;
    }

   // pushStrategy.push(file);
  }

  /**
   * Store a file's hash in a subfolder of the team's hidden configuration folder. 
   * The subfolder's name is the filename's hash.
   * @param file 
   * @param filePath - the absolute path of the file
   * @throws IOException
   */
  private void hashFile(TeamFile file, Path filePath) throws IOException {

    //TODO remove hardcode "files" and "hash"

    Path hiddenPath = session.getHiddenTeamFilesFolder(file.getTeam());

    if (!Files.isDirectory(filePath)) { 

      // obtain the path of the folder in which to store the hash
      String fileNameHash = new String (file.getHash());
      String s = hiddenPath.toString() + FileSystems.getDefault().getSeparator().charAt(0) +fileNameHash;
      Path keyPath = FileSystems.getDefault().getPath(s);

      if (!Files.isDirectory(keyPath)  || !Files.exists(keyPath)) {
        Files.createDirectory(keyPath);
        logger.debug(String.format("Created folder %s for file %s' hash", keyPath, file));
      }     

      byte[] hash = computeFileHash(filePath);      
      logger.debug(String.format("Created hash for file %s", file));            

      s = keyPath.toString() + FileSystems.getDefault().getSeparator().charAt(0) + "hash";
      Path hashFilePath = FileSystems.getDefault().getPath(s);
      Files.write(hashFilePath, hash, StandardOpenOption.CREATE, 
          StandardOpenOption.WRITE, 
          StandardOpenOption.TRUNCATE_EXISTING);

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
      input.close();
    }

    return md.digest();
  }
}

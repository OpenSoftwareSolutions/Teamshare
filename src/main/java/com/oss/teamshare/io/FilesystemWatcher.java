/**
 * 
 */
package com.oss.teamshare.io;


import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oss.teamshare.sync.Synchronization;
import com.oss.teamshare.team.DeviceId;
import com.oss.teamshare.team.Session;
import com.oss.teamshare.team.Team;
import com.oss.teamshare.team.UserId;
/**
 * 
 *
 */
public class FilesystemWatcher extends Thread {

  WatchService watcher;

  /**
   * The team's folder absolute path is ${TEAMSHARE_PATH}/${TEAM_PATH}.
   */
  private Path teamAbsolutePath;
  private HashMap<WatchKey, Path> watchDirs;

  private Logger logger = LogManager.getLogger("FilesystemWatcher");

  private Synchronization syncService;

  private List<Path> excludedDirs;
  
  public FilesystemWatcher(Path teamPath, Synchronization syncService){

    super();
    this.syncService = syncService;
    this.teamAbsolutePath = teamPath;
    
  }

  /**
   * Start monitoring/watching the filesystem for changes.
   * @param recursive
   * @throws Exception
   */
  public void watch(Boolean recursive, List<Path> exclude){
    try{
    watcher = FileSystems.getDefault().newWatchService();
    watchDirs = new HashMap<WatchKey, Path>();
    excludedDirs = exclude;
    Files.walkFileTree(teamAbsolutePath, new SimpleFileVisitor<Path>() {
      @Override
      public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
      {
        for (Path excluded: excludedDirs)
        {
          try {
           
            if (dir.startsWith(excluded))
              return FileVisitResult.CONTINUE;
          }catch (IllegalArgumentException e){
            
          }
        }
       
        register(dir);
        return FileVisitResult.CONTINUE;
      }
    });
    } catch (IOException ioe){
      logger.fatal(String.format("Exception registering team folder %s: %s\n", teamAbsolutePath, ioe.getMessage()));
      System.exit(0); //if there is an exception creating the watcher -> fatal error, can't monitor filesystem for changes 
    }
  }

  @SuppressWarnings("unchecked")
  public void run() {

    for(;;){

      WatchKey key;
      try {
        try {
          key = watcher.take();
        } catch(ClosedWatchServiceException ex){
          return;
        }
        Path dir = watchDirs.get(key);
  
        if (dir == null) {
          logger.error("WatchKey not recognized!!");
          continue;
        }

        for (WatchEvent<?> event: key.pollEvents()) {
          WatchEvent.Kind<Path> kind = (WatchEvent.Kind<Path>)event.kind();
          WatchEvent<Path> ev = (WatchEvent<Path>) event;

          Path name = ev.context();       // file name
          Path filePath = dir.resolve(name); // absolute path
          logger.debug(String.format("%s: %s\n", event.kind().name(), filePath));

          if (Files.isHidden(filePath)) {
            continue;
          }
         
          FilesystemEvent tsEvent = null;
          
          if (kind.equals(StandardWatchEventKinds.ENTRY_CREATE)){

            if  (Files.isDirectory(filePath)){
              register(filePath);
            }
            tsEvent = new FilesystemEvent(filePath, FileEventType.CREATE);             
          }

          else if (kind.equals(StandardWatchEventKinds.ENTRY_DELETE)){

            if  (Files.isDirectory(filePath)){
              watchDirs.remove(key);             
            }
            tsEvent = new FilesystemEvent(filePath, FileEventType.DELETE); 
          }

          else if (kind.equals(StandardWatchEventKinds.ENTRY_MODIFY)){
            tsEvent = new FilesystemEvent(filePath, FileEventType.MODIFY); 
          }
          
          if (tsEvent != null)
            syncService.notifyFilesystemEvent(tsEvent);

        }
                
        key.reset();

      } catch (Exception x) {
        logger.fatal("Exception processing events \n", x.getMessage());
        return;
      }
       
     }
      
    }

    private void register (Path dir) {
      try {
        WatchKey key = dir.register(watcher, 
            StandardWatchEventKinds.ENTRY_CREATE, 
            StandardWatchEventKinds.ENTRY_DELETE, 
            StandardWatchEventKinds.ENTRY_MODIFY);

        Path prev = watchDirs.get(key);

        if (prev == null) {
          logger.info("register: %s\n", dir);
        } else {
          if (!dir.equals(prev)) {
            logger.info("update: %s -> %s\n", prev, dir);
          }
        }

        watchDirs.put(key, dir);

      }catch (IOException ioe) {
        logger.error(String.format("Exception registering folder %s: %s\n", teamAbsolutePath, ioe.getMessage()));
      }
    }
    
    public void stopWatching(){
      try{
        watcher.close();
      }catch(IOException ioe){
        logger.error(String.format("Exception while stopping watcher %s", ioe.getMessage()));
      }
      
    }
    
    public static void main (String []args) {
      FileSystem fs = FileSystems.getDefault();
      Iterable<Path> dirs = fs.getRootDirectories();
      for (Path p : dirs) {
        System.out.println("uri " + p.toUri() + " string " + p);
      } 
      try{
        String teamPath = "/home/adriana/Teamshare/Img";
        UserId userId = new UserId("adriana");
        DeviceId deviceId = new DeviceId("adriana_laptop");
        //String strPort = System.getProperty("teamshare.port");
        //int port = Integer.parseInt(strPort);
        int port = 6772;
        //Team team = new Team (new TeamId("1234"), "ateam", new User(userId, "adriana"));
        Path dir = FileSystems.getDefault().getPath(teamPath);
        Session session = Session.create(userId, deviceId, port);
       
        Synchronization syncService = new Synchronization(session);        
        FilesystemWatcher watcher = new FilesystemWatcher(dir, syncService); // not yet session.getPath() 
              
        ArrayList<Path> excludedDirs = new ArrayList<Path>();
        for (Team team : session.getTeams()){
          excludedDirs.add(session.getHiddenTeamFolder(team));
        }
        watcher.watch(true, excludedDirs);
        
        watcher.start();
        
        System.out.println("Press any key to exit...");
        System.in.read();

        watcher.stopWatching();
        watcher.join();
       
        
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }


  }

package com.oss.teamshare.team;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTeamRepo implements TeamRepo {
  
  private static final String TEAMS_FILENAME = "data/prototype/teams01.json";
  private static final String USERS_FILENAME = "data/prototype/users01.json";
  
  private Map<UserId, User> users;
  
  public static Logger logger = LogManager.getLogger(JsonTeamRepo.class);
  
  /**
   * Retrieve from team repository all teams in which a user is part of.
   * 
   * @throws TeamRepoException
   */
  @Override
  public Map<TeamId, Team> retrieveUserTeams(UserId userId, DeviceId deviceId)
      throws TeamRepoException {
    Map<TeamId, Team> teams = new LinkedHashMap<>();
    ObjectMapper mapper = new ObjectMapper();
    
    /* Load users and their devices */
    users = readUsersFromJson(deviceId);
    logger.info("Loaded " + users.size() + " users.");
    
    JsonNode rootNode;
    try {
      rootNode = mapper.readValue(new File(TEAMS_FILENAME), JsonNode.class);
    } catch (IOException e) {
      throw new TeamRepoException("could not open JSON file '"
          + TEAMS_FILENAME + "'", e);
    }
    
    JsonNode teamsNode = rootNode.path("teams");
    for (JsonNode teamNode : teamsNode) {
      String strId = teamNode.get("id").textValue();
      String name = teamNode.get("name").textValue();
      String strOwner = teamNode.get("owner").textValue();
      if (strId == null || name == null || strOwner == null) {
        throw new IllegalArgumentException(
            "id, name and owner fields are mandatory for a team");
      }
      
      Team team = new Team(new TeamId(strId), name, users.get(new UserId(strOwner)));
      
      JsonNode usersNode = teamNode.path("users");
      for (JsonNode userNode : usersNode) {
        String strUser = userNode.textValue();
        if (strUser != null && users.containsKey(new UserId(strUser))) {
          team.addUser(users.get(new UserId(strUser)));
        }
      }
      
      if (team.hasUser(userId)) {
        teams.put(team.getId(), team);
      }
    }
    
    return teams;
  }
  
  /**
   * Read a JSON file which contains data for all registered users and their
   * attached devices.
   * 
   * myDeviceId represents the device which runs this
   * application instance and will not be added, because the logged in
   * user does not have to connect to its own device.
   * 
   * @param myDeviceId the 
   * @return
   * @throws TeamRepoException
   */
  private Map<UserId, User> readUsersFromJson(DeviceId myDeviceId)
      throws TeamRepoException {
    Map<UserId, User> users = new LinkedHashMap<>();
    ObjectMapper mapper = new ObjectMapper();
    
    JsonNode rootNode;
    try {
      rootNode = mapper.readValue(new File(USERS_FILENAME), JsonNode.class);
    } catch (IOException e) {
      throw new TeamRepoException("could not open JSON file '"
          + USERS_FILENAME + "'", e);
    }
    
    JsonNode usersNode = rootNode.path("users");
    for (JsonNode userNode : usersNode) {
      String strId = userNode.get("id").textValue();
      String username = userNode.get("username").textValue();
      JsonNode devicesNode = userNode.get("devices");
      if (strId == null || username == null || devicesNode == null) {
        throw new IllegalArgumentException(
            "id, username and devices fields are mandatory for a user");
      }
      
      User user = new User(new UserId(strId), username);
      
      for (JsonNode deviceNode : devicesNode) {
        String strDevId = deviceNode.get("id").textValue();
        String devName = deviceNode.get("name").textValue();
        String addr = deviceNode.get("address").textValue();
        if (strId == null || devName == null || addr == null) {
          throw new IllegalArgumentException(
              "id, name and address fields are mandatory for a device");
        }
        
        InetSocketAddress isa;
        StringTokenizer strtok = new StringTokenizer(addr, ":");
        try {
          isa = new InetSocketAddress(strtok.nextToken(),
              Integer.parseInt(strtok.nextToken()));
        } catch (NoSuchElementException e) {
          throw new IllegalArgumentException("Provide address as <host>:<port>");
        } catch (NumberFormatException e) {
          throw new IllegalArgumentException("Invalid address port.");
        }

        DeviceId devId = new DeviceId(strDevId);
        if (!devId.equals(myDeviceId)) {
          Device device = new Device(devId, devName, user, isa);
          user.addDevice(device);
        }
      }
      
      users.put(user.getId(), user);
    }
    
    return users;
  }

}

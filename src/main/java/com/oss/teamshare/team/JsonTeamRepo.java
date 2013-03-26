package com.oss.teamshare.team;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTeamRepo implements TeamRepo {
  
  private static final String TEAMS_FILENAME = "data/prototype/teams01.json";
  private static final String USERS_FILENAME = "data/prototype/users01.json";
  
  private Map<UserId, User> users;
  
  public void init() throws TeamRepoException {
    if (users == null) {
      users = readUsersFromJson();
    }
  }

  /**
   * Retrieve from team repository all teams in which a user is part of.
   * 
   * @throws TeamRepoException
   */
  @Override
  public Map<TeamId, Team> retrieveUserTeams(UserId userId)
      throws TeamRepoException {
    Map<TeamId, Team> teams = new LinkedHashMap<>();
    ObjectMapper mapper = new ObjectMapper();
    init();

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
      
      teams.put(team.getId(), team);
    }
    
    return teams;
  }
  
  /**
   * Read a JSON file which contains data for all registered users and thier
   * attached devices
   * 
   * @return
   * @throws TeamRepoException
   */
  private Map<UserId, User> readUsersFromJson() throws TeamRepoException {
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
        
        Device device = new Device(new DeviceId(strDevId), devName, user);
        user.addDevice(device);
      }
      
      users.put(user.getId(), user);
    }
    
    return users;
  }

}

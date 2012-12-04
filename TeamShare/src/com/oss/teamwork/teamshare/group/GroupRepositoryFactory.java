/**
 * 
 */
package com.oss.teamwork.teamshare.group;


/**
 * Provides the GroupRepository Singleton implementation given in the Configuration file.
 *
 */
public class GroupRepositoryFactory {
  private static GroupRepositoryFactory INSTANCE;
  private GroupRepositoryFactory(){
    
  }
  public static GroupRepositoryFactory getInstance() {
    if (INSTANCE == null) 
      INSTANCE = new GroupRepositoryFactory();
    return INSTANCE;
  }
  
  public GroupRepository getGroupRepository(){
    //TODO implement me
    return null;
  }
 
  
}

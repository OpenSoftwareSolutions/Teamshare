package com.oss.teamwork.teamshare.group;

import java.util.Collection;


public interface GroupRepository {
  
  

  /**
   * Returns a list of devices from the group based on a query criteria (e.g devices for 
   * @param group
   * @param query
   * @return
   */
  public Collection<Device> getDevices(Group group, DevicesQuery query);
  
  public void addDevice(User user, Device device);
  
  public void updateDevice(Device device);
  
  /**
   * Trigger update group information from the group file.
   * 
   * @param group
   */
  public void update(Group group);
  
  /**
   * Returns the Group that contains the given file (inspects the root folder in the file's path)
   * @param file - //TODO java.io.File or our file?
   * @return
   */
  public Group getGroup(java.io.File file);
}

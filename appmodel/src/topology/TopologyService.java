/**
 * 
 */
package topology;

import groupmanagement.Group;

import java.util.HashMap;
import java.util.List;

import common.Device;


/**
 * 
 * Should be singleton
 * @author adriana
 *
 */
public class TopologyService {

	private HashMap<Long, Overlay> overlays;
	
	/**
	 * Provides a subset of devices in a group which will be used for the PULL action by another component(s)
	 * @param group the group from which the changes will be pulled
	 * @return a list of Device objects representing the available devices
	 */
	public List<Device> getDevicesForPull(Group group){return null;}
	/**
	 * Provides a subset of devices in a group which will be used for the PUSH action by another component(s)
	 * @param group the group from which the changes will be pushed
	 * @return a list of Device objects representing the available devices
	 */
	public List<Device> getDevicesForPush(Group group){return null;}
	/**
	 * Used by other components to check if a device is online or not
	 * @param device
	 * @return true if the device is online, false if offline, null if the device is no longer registered.
	 */
	public Boolean isDeviceAvailable(Device device){return false;}
	/**
	 * Provides a list of all devices registered for the members in a group, 
	 * either offline or online.
	 * @param group 
	 * @return the list of devices, not necessarily order by group member (User)
	 */
	public List<Device> getAllDevices(Group group){return null;}

	/** 
	 * TopologyService is informed about an update in the group's structure (e.g. members). 
	 * This will generate updates of the overlay.
	 * @param g the group that was updated by the other components
	 */
	public void groupUpdate(Group g){}
	
	/**
	 * Invoked when the application is no longer connected to an internet connection. 
	 * The Topology component will inform the remote services about this change of device status. 
	 */
	public void goOffline() {}
	
}
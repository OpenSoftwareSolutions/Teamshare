package topology;

import java.util.ArrayList;

import common.Device;
import common.Group;

/**
 * 
 * A logical view of a group's online devices and connections between them. 
 *
 */
public abstract class GroupOverlay {
	/**
	 * The online devices linked to <b>users</b> in the <b>group</b>
	 */
	ArrayList<Device> devices;
	/**
	 * A reference to the group for which this overlay is maintained.
	 */
	Group group;
		
	/**
	 * Returns the list of devices in this overlay
	 */
	public abstract ArrayList<Device> getDevices();
}

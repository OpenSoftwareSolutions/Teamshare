package topology;

import java.util.ArrayList;

import common.Device;
import common.Group;

/**
 *
 * A dynamic view of a group's online devices and connections among them. Each group has its
 * own overlay. The overlay is constantly changing depending on the connectivity of the devices in the group.
 *
 */
public abstract class GroupOverlay {
	/**
	 * The online devices linked to <b>users</b> in the <b>group</b>. This list is a subset of the
	 * devices linked to users in the group.
	 */
	protected ArrayList<Device> devices;

	/**
	 * A reference to the group for which this overlay is maintained.
	 */
	Group group;

	/**
	 * Returns the list of devices in this overlay.
	 */
	public abstract ArrayList<Device> getDevices();
}

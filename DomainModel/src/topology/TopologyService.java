package topology;

import java.util.List;

import common.Device;
import common.Group;

/**
 * Implements the service-type component for the {@link topology} module. It is the entry-point for this module,
 * offering an interface to the actions performed by the OverlayManagement. This module deals only with the
 * dynamic view of groups' devices, overalays, obtaining and managing information needed for communicating with 
 * other devices (e.g. for synchronization purposes).
 *
 */
public abstract class TopologyService {
	
	/**
	 * TopologyService uses the OverlayManagement component for retrieving overlay data, or informing it about
	 * updates.
	 */
	OverlayManagement overlayManagement;
	
	/**
	 * Returns the devices in the given group's overlay. This method is invoked by the {@link SynchronizationService} 
	 * when starting a synchronization process.
	 * @param group - the group for which the devices are requested 
	 * @return a list of Device objects that represent the online devices and contain their connectivity
	 * information
	 */
	public abstract List<Device> getDevices(Group group);
	
	/**
	 * The {@link topology} module is informed that a <b>group change</b> related to user dynamics has occurred, and 
	 * it needs to update the <b>overlay<b> for that group<b>.
	 * @param group
	 */
	public abstract void updateGroupOverlay(Group group);
	
	/**
	 *   The {@link topology} module starts building the group overlays.
	 */
	public abstract void buildGroupOverlays();
}

package topology;

import java.util.List;

import network.DiscoveryServiceRequest;
import network.NamingServiceRequest;
import network.NetworkService;

import common.Device;
import common.Group;

/**
 * Implements the service-type component for the {@link topology} module. It is the entry-point for this module,
 * offering an interface to the actions performed by the OverlayManagement. This module deals only with the
 * dynamic view of groups' devices, overlays, retrieving and managing information needed for communicating with
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
	 * The communication between the TopologyService and the remote services goes through the network module.
	 * TopologyService uses the NetworkService to send requests for information about devices.
	 */
	NetworkService networkService;
	
	/**
	 * Returns the devices in the given group's overlay. This method is invoked by the {@link SynchronizationService}
	 * when starting a synchronization process.
	 * @param group - the group for which the devices are requested
	 * @return a list of Device objects that represent the online devices and contain their connectivity
	 * information.
	 */
	public abstract List<Device> getDevices(Group group);

	/**
	 * The {@link topology} module is informed that a <b>group change</b> related to user dynamics has occurred, and
	 * needs to update the <b>overlay<b> for that group<b>.
	 * @param group
	 */
	public abstract void updateGroupOverlay(Group group);

	/**
	 * The {@link topology} module starts building the group overlays.
	 */
	public abstract void buildGroupOverlays();
	
	/**
	 * The {@link OverlayManagement} sends requests to the Discovery Service via the Topology Service 
	 * (the exit-point for this module). The TopologyService then forwards it to the NetworkService, 
	 * the {@link OverlayManagement} doesn't need to hold this information (which component does the
	 *  communication with remote services).
	 * @param request - the request sent to the Discovery Service
	 */
	public abstract void sendRequest(DiscoveryServiceRequest request);
	
	/**
	 * The {@link OverlayManagement} sends requests to the Naming Service via the Topology Service 
	 * (the exit-point for this module). The TopologyService then forwards it to the NetworkService, 
	 * the {@link OverlayManagement} doesn't need to hold this information (which component does the
	 *  communication with remote services).
	 * @param request - the request sent to the Naming Service 
	 */
	public abstract void sendRequest(NamingServiceRequest request);
	
}

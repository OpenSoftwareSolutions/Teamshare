package topology;

import java.util.List;

import common.Group;

/**
 * Implements the component in charge of creating and maintaining <b>overlays</b>
 * for all the logged-in <b>user's groups</b>.
 *
 */
public abstract class OverlayManagement {

	/**
	 * The OverlayManagement creates and modifies these group overlays.
	 */
	List<GroupOverlay> groupOverlays;

	/**
	 * The OverlayManagement uses the network module via the Topology Service. 
	 */
	TopologyService topologyService;
	
	/**
	 * Creates an Overlay for a specific group.
	 */
	public abstract void createOverlay(Group group);
	
	/**
	 * Updates an Overlay for a specific group.
	 * E.g. invoked when a group change took place: a new device was linked/unlinked, a new user joined the group
	 * a user left/was removed from the group etc.
	 */
	public abstract void updateOverlay(Group group);
	
	/**
	 * Deletes an overlay maintained for a group. E.g. invoked when the logged in
	 * user is no longer part of that group.
	 * E.g. invoked when a group change took place: a new device was linked/unlinked, a new user joined the group
	 * a user left/was removed from the group etc.
	 */
	public abstract void clearOverlay(Group group);
}

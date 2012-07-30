package topology;

import java.util.List;

import common.Group;

/**
 * Implements the component in charge of creating and maintaining <b>overlays</b> 
 * for all the logged-in <b>user's groups</b>.
 *
 */
public abstract class OverlayManagement {
	
	List<GroupOverlay> groupOverlays;
	
	/**
	 * Creates an Overlay for a specific group.
	 */
	public abstract void createOverlay(Group group);
	/**
	 * Updates an Overlay for a specific group 
	 * (e.g. invoked when a group change took place: a new device was linked/unlinked, a new user joined the group
	 * a user left/was removed from the group etc
	 */
	public abstract void updateOverlay(Group group);
	/**
	 * Deletes an overlay maintained for a group, e.g. invoked when the logged in 
	 * user is no longer part of that group.
	 * (e.g. invoked when a group change took place: a new device was linked/unlinked, a new user joined the group
	 * a user left/was removed from the group etc
	 */
	public abstract void clearOverlay(Group group);
}

//OverlayManagement overlayManagement;

/*public abstract List<Device> getDevices();
public abstract void updateGroupOverlay(Group group);*/

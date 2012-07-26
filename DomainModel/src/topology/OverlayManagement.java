package topology;

import common.Group;

public abstract class OverlayManagement {
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

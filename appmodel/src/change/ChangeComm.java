package change;

import common.Device;


/**
 * Class that deals with the communication between this Change Component and other such components
 *  from other users' devices
 * @author adriana
 *
 */
public class ChangeComm {

	/**
	 * Performed as part of a PUSH operation: it informs the pushed data recipient about the synced changes
	 * 
	 * @param device the Device representing the receiver of the pushed data 
	 * @param change the Change object with the changes that were pushed
	 */
	public void sendPushMessage(Change change, Device device) {}
	
	/**
	 * Invoked when receiving a message from the Change Component of another device.
	 * @param message
	 */
	public void receiveMessage(ChangeMessage message){}
	
}

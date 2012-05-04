package communication;

import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * Class that handles the newtork communication between the components on the local device and the components on other devices.
 * It basically relays and listens for messages
 * @author adriana
 *
 */
public class CommunicationManager {

	/**
	 * Sends the message (serialized as a data stream) to the given address.
	 * @param message the message to be send, previously serialized 
	 * @param destination the address of the device
	 */
	public void send (OutputStream message, InetSocketAddress destination){}
	
	public void listen(){}
	
}

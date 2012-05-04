package communication;

import java.net.InetSocketAddress;

import common.Message;

import change.ChangeMessage;

/**
 * Entry point for the component in charge of network communication. 
 * @author adriana
 *
 */
public class CommunicationService {

	/**
	 * Serializes the message, extracts its destination and sends it.
	 * @param message
	 */
	public void sendMessage(Message message){}
	/**
	 * Serializes the message, extracts its destination and sends it.
	 * @param message a message exchanged Change Components
	 */
	public void sendChangeMessage(ChangeMessage message){}
	/**
	 * 
	 * @param type the type of the centralized remote service
	 * @return the ip address and port of the service
	 */
	public InetSocketAddress getRemoteServiceAddress(RemoteServiceType type){ return null;}
	
	
}

package network;

import topology.TopologyService;
import messaging.Message;
import messaging.MessagingService;

/**
 * Implements a network listener, waiting for communication from other devices or from remote services.
 * It parses the incoming communication and forwards it to other components.
 *
 */
public abstract class NetworkListener {

	TopologyService topologyService;
	MessagingService messagingService;

	/**
	 * Receives a message from another user via the remote MessagingService.
	 * Forwards the message to the {@link messaging} module.
	 * @param message - the Message from another user.
	 */
	protected abstract void receiveMessage(Message message);
	/**
	 * Receives reply to a request from the Discovery Service. Forwards it to the {@link topology} module.
	 * (uses {@link TopologyService}).
	 * @param reply - the information received from DS
	 */
	protected abstract void receiveReplyFromDS(Object reply);
	/**
	 * Receives  reply to a request from the Naming Service. Forwards it to the component from which the
	 * request originated.
	 * @param reply - the information received
	 */
	protected abstract void receiveReplyFromNS(Object reply);
	/**
	 * Receives  reply to a request from the Authentication Service. Forwards it to the component from which
	 * the request originated.
	 * @param reply - the information received
	 */
	protected abstract void receiveReplyFromAS(Object reply);
	
	
}

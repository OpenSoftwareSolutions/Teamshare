package network;

import common.Account;
import common.Device;

import messaging.Message;
import messaging.MessagingService;

public abstract class NetworkService {
	MessagingService messagingService;
	Communication communication;

	/**
	 * Sends the given request to the remote Discovery Service.
	 * @param request - the request, its type and content as described in the user stories
	 */
	public abstract void contactRemoteService(DiscoveryServiceRequest request);
	/**
	 * Sends the given request to the remote Naming Service.
	 * @param request - the request, its type and content as described in the user stories
	 */
	public abstract void contactRemoteService(NamingServiceRequest request);
	/**
	 * Send the given request to the remote Naming Service.
	 * @param message - the message sent by the logged-in user to another user.
	 */
	public abstract void contactRemoteService(Message message);
	
	/**
	 * Send the given request to the remote Authentication Service.
	 * @param request - the request, its type and content as described in the user stories
	 */
	public abstract void contactRemoteService(AuthenticationServiceRequest request);

	/**
	 * Checks if this device is still connected to the internet (online).
	 */
	protected abstract void checkForConnectivity();
}

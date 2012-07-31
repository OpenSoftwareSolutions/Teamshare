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
	 * Sends device information to the Authentication Service in order to authenticate the device.
	 * @param device
	 */
	public abstract void authenticateDevice(Device device);
	/**
	 * Sends user credentials to the Authentication Service in order to authenticate the user
	 * (necessary when the user logs in).
	 * @param account - the use's account information
	 * @param credentials - the password the user input or some other form of security credentials
	 */
	public abstract void authenticateUser(Account account, String credentials);

	//TODO description
	protected abstract void checkForConnectivity();
}

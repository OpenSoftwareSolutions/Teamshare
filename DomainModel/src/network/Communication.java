package network;

import common.ConnectivityInfo;

import security.Crypto;

/**
 * The component closest to the network level, responsible for sending data on the network and
 * respecting the Teamshare security policies (communication is encrypted).
 *
 */
public abstract class Communication {
	/**
	 * Uses crypto from the the {@link security} module to encrypt/decrypt communication.
	 */
	Crypto crypto;

	/**
	 * Sends data over the network to the IP address and port given by the destination, first encrypting it.
	 * @param data - the data to be sent; tts type is not relevant at this level
	 * @param destination - the connectivity information (IP address and port) of a remote service or another device.
	 */
	public abstract void sendData(Object data, ConnectivityInfo destination);
}

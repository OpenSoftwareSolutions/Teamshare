package network;

import common.ConnectivityInfo;

/**
 * Implements a value-object type component, which is used for communication between a device and the
 * Discovery Service. It represents the type of request and its content.
 *
 */
public class DiscoveryServiceRequest {
	/**
	 * The deviceID of the device for which the request is made. It can also be the same device that
	 * sends the request (e.g. in case of publish/unpublish).
	 */
	public String deviceID;
	/**
	 * The type of request that needs to be sent by the device to the DiscoveryService, as defined by
	 * DSRequestType.
	 */
	public DSRequestType request;
	/**
	 * It is used only when publishing a device, otherwise is null.
	 */
	public ConnectivityInfo connectivityInfo;
}

/**
 * The types of request that a device can send to the DiscoveryService. These are the requests described
 * in the DiscoveryService <a href="https://koala.cs.pub.ro/teamwork/wiki/topics/userstories#device-discovery">user stories</a>.
 *
 */
enum DSRequestType{
	DEVICE_STATUS,
	DEVICE_CONN_INFO,
	MARK_OFFLINE,
	PUBLISH_DEVICE,
	UNPUBLISH_DEVICE,
	KEEP_ALIVE_MSG
}

package network;

/**
 * Implements a value-object type component, used for communication between a device and the
 * Naming Service. It represents the type of request and its content.
 *
 */
public class NamingServiceRequest {
	/**
	 * The type of request that needs to be sent by the device to the NamingService, as defined by
	 * NSRequestType.
	 */
	public NSRequestType request;
	/**
	 * The request's content, (e.g. an user ID when asking for a user's devices(GET_USER_DEVICES),
	 *  some device's information for linking).
	 */
	public Object requestContent;
}

enum NSRequestType{
	LINK_DEVICE,
	UNLINK_DEVICE,
	GET_USER_DEVICES
}

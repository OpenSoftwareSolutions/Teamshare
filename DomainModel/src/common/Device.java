package common;


/**
 * Implementation of the <b>Device</b> entity. It represents the device on which the Teamshare application is installed.
 * It is associated with an user account. If another user logs in and links the device, the created Device object is different.
 *
 */
public class Device {
	/**
	 * The device's unique identifier, provided when the device is linked. It is not visible to the user.
	 */
	public long deviceID;

	/**
	 * The device's name, selected by the user when linking the device.
	 * It is visible to the user when visualizing the list of his devices. It must be unique for each user.
	 */
	public String name;

	/**
	 * The device's connectivity information: IP address and port. {@link ConnectivityInfo} has the role of a value object
	 * that contains the connectivity attributes. 
	 * This field is non-null while the device in online and null while offline.
	 */
	public ConnectivityInfo address;

	/**
	 * The device's status: true if online, false if offline. A device is online when it can connect and communicate with other devices.
	 */
	public boolean online;

	/**
	 * For security mechanisms, the devices may also have one ore more keys. The exact type and usage is described at application level.
	 */
	public String publickey;
}

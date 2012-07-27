package common;

/**
 *  This class implements a value object component that represents the <b>connectivity information</b> of a <b>device</b>. This information is defined by the
 *  IP address at which the <b>device</b> can be reached and its port. 
 * 
 */
public class ConnectivityInfo {
	/**
	 * The device's IP address.
	 */
	public byte[] address;
	/**
	 * The device's port;
	 */
	public int port;
	
	ConnectivityInfo(byte[]address, int port){}
	
	/**
	 * Invoked when the device becomes offline (lost connectivity)
	 */
	public void clear(){}
}

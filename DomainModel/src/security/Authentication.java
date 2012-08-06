package security;

import common.Account;
import common.Device;

public abstract class Authentication {
	
	
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
	
}

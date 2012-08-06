package network;

/**
 * The command/request sent by the Authentication component to the remote Authentication Service for
 * verifying device or user credentials at log in.
 *
 */
public class AuthenticationServiceRequest {
	public long deviceID;
	public long usedID;
	public String credentials;
}
enum ASRequestType{
	DEVICE_AUTH,
	USER_AUTH
}
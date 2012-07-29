package security;
/**
 * Implements a service-type component for the security module. It is not the only entry-point to this module, 
 * the Crypto module being used directly by the SynchronizationStrategies and NetworkListener.
 *
 */
public abstract class SecurityService {
	
	SecureTokenGenerator tokenGenerator;
	KeyGenerator keyGenerator;
	
	/**
	 * Generates and returns a secure token, which can be used when sending invitations. Uses {@link SecureTokenGenerator}.
	 * @return the token object
	 */
	public abstract Object getSecureToken();
	/**
	 * Generates and returns keys used by groups, devices and users.
	 * @return the key/keys	
	 */
	public abstract Object getKeys();
	
}

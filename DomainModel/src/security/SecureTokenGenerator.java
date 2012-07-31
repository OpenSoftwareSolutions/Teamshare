package security;

public abstract class SecureTokenGenerator {
	/**
	 * Generates and returns a secure token. The token will be used when sending invitations. Uses {@link SecureTokenGenerator}.
	 * @return the token object
	 */
	public abstract Object generateSecureToken();
}

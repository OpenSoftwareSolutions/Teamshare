/**
 * 
 */
package cryptography;

import java.security.Key;

/**
 * Interface that must be implemented by classes that uses java security algorithms (or providers)
 * @author adriana
 *
 */
public interface KeyGenerator {

	public Key generateKeys();
	public Key generateSymKey();
}

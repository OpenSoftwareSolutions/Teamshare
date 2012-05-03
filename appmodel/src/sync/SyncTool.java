/**
 * 
 */
package sync;

import java.net.InetAddress;

/**
 * Interface implemented by classes that perform synchornization (e.g. use rsync)
 * @author adriana
 *
 */
public interface SyncTool {

	public void sync(String path, InetAddress destination);
	
}

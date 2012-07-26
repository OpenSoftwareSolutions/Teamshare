package change;

import java.util.Date;

/**
 * A Factory-type component for creating Change objects, either <b>filesystem or group changes</b>.
 * Components from other modules (Filesystem and Group modules) can access it directly for creating 
 * <i>Change<i> entities.
 */
public abstract class ChangeFactory {
	
	/**
	 * Creates change objects of all supported types.
	 * @param type - the change's type (file, folder, group)
	 * @param changeTarget - the entity to which this change is related: a specific file or folder, a specific group. 
	 * 						The Change's attributes are filled based on this object's type.
	 * @param changeDetails - details about what was performed that lead to this change (modified, added or removed).
	 * @param timestamp - the time when this change occurred.
	 * @return
	 */
	public abstract Change createGroupChange(ChangeType type, 
											Object changeTarget, 
											String changeDetails, 
											Date timestamp);
	
	/*public abstract Change createGroupChange(ChangeType type, String changedGroup, String changeDetails, Date timestamp);
	public abstract Change createFileChange(ChangeType type, String changedFile, String changeDetails, Date timestamp);//TODO arguments
	public abstract Change createFolderChange(ChangeType type, String changedGroup, String changeDetails, Date timestamp);//TODO arguments
	*/
}

package change;

import java.util.Date;

import common.Group;

import filesystem.File;
import filesystem.Folder;

/**
 * Change entity represents filesystem and group changes (updates). 
 *  
 */
public class Change {
	
	/**
	 * The device's local time when the change was made.
	 */
	public Date timestamp;
	
	/**
	 * The type of change: group, folder or file-related. 
	 */
	public ChangeType type;
	
	/**
	 * File change: created, modified, removed
	 */
	public File changedFile;
	/**
	 * Group change : user add/remove, ownership transfer, groups settings change
	 */
	public Group changedGroup;
	/**
	 * If a folder is created or removed only one change object is made (there are no change 
	 * objects created for each file in the removed folder)
	 */
	public Folder changedFolder;
	
	/**
	 * Details regarding the change, what was performed/modified that led to it. At implementation level these details will be 
	 * expressed differently (not just using a simple String).
	 */
	public String changeDetails;
	
}

package filesystem;

import java.util.List;
import java.util.Date;

import change.Change;

/**
 * Implements the Folder entity component. It represents a subfolder in a group's folder.
 *
 */
public class Folder {
	/**
	 * The folder's name, as seen by the user.
	 */
	public String name;
	/**
	 * The folder's path, excluding the folder's name.
	 */
	public String path;
	/**
	 * The folder's version, given by the versioning system.
	 */
	public long version;
	/**
	 * The time when the folder was last modified locally
	 */
	public Date lastModified;
	/**
	 * A reference to its last change.
	 */
	public Change lastChange;
	/**
	 * Used for mobile devices, true if the file is important (represents a user's <i>favorite folder</i>)
	 */
	public boolean important;
	/**
	 * The list of file inside it.
	 */
	public List<File> files;
	/**
	 * The list of folders inside it.
	 */
	public List<Folder> subfolders;
}

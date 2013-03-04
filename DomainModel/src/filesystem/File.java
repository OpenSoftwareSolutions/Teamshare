package filesystem;

import org.joda.time.DateTime;

import change.Change;

/**
 * Implementation of the File entity component. It represents a file inside a group's folder.
 *
 */
public class File {
	/**
	 * The file's name, as seen by the user.
	 */
	public String name;
	/**
	 * The file's path, excluding the file's name.
	 */
	public String path;
	/**
	 * The file's version, given by the versioning system.
	 */
	public long version;
	/**
	 * The time when the file was last modified locally.
	 */
	public DateTime lastModified;
	/**
	 * A reference to its last change.
	 */
	public Change lastChange;
	/**
	 * True if the files has copies made by a conflict resolution strategy.
	 */
	public boolean hasCopies;
	/**
	 * The file's type.
	 */
	public String type;
	/**
	 * The name of the application installed on the system for opening this type of file.
	 */
	public String openApp;
	/**
	 * Used for mobile devices, true if the file is important (represents a user's <i>favorite file</i> or an <i>active file</i>
	 */
	public boolean important;
}

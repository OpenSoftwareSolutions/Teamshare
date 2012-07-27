package filesystem;

/**
 * Represents the component that monitors the filesystem for changes and informs the FilesystemServic about them.
 *
 */
public abstract class FilesystemListener {
	
	/**
	 * Reference to the {@link FilesystemService} which it informs when files or folders have been changed. 
	 * The Change objects are created by the FilesystemService.
	 */
	FilesystemService filesystemService;
	
	/**
	 * Informs the filesystemService about a file change (creation, modification, removal)
	 * @param file
	 */
	protected abstract void informChange(File file);
	
	/**
	 * Informs the filesystemService about a folder change (creation, removal)
	 * @param folder
	 */
	protected abstract void  informChange(Folder folder);
}

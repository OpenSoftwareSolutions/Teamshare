package filesystem;

import java.util.List;

import change.Change;
import change.ChangeFactory;
import change.ChangeService;
import synchronization.SynchronizationService;

/**
 * Implements the Filesystem Service component. Its roles are to be the entry-point to the {@link filesystem} module functionalities for 
 * components from other modules and to inform other components about filesystem changes.
 *
 */
public abstract class FilesystemService {
	/**
	 * Reference to the {@link SynchronizationService} which is the interface to the synchronization mechanisms.
	 */
	protected SynchronizationService syncService;
	/**
	 * Reference to the {@link ChangeService}, which FilesystemService uses for logging Change objects
	 */
	protected ChangeService changeService;
	/**
	 * Reference to the {@link ChangeFactory}, which FilesystemService uses for creating Change objects
	 */
	protected ChangeFactory changeFactory;
	
	/**
	 * Is informed by the {@link SynchronizationService} about a new change.
	 * @param change - a change that was applied during synchronization
	 */
	public abstract void receiveChange(Change change);
	/**
	 * Is informed by the {@link SynchronizationService} about new changes.
	 * @param changes - the changes that were applied during synchronization
	 */
	public abstract void receiveChanges(List<Change> changes);
	/**
	 * Informs the {@link SynchronizationService} about a filesystem Change that needs to be synchronized.
	 * @param change 
	 */
	protected abstract void informSyncChange(Change change);
	/**
	 * Informs the {@link ChangeService} about a filesystem Change that needs to be logged in the history of changes.
	 * @param change
	 */
	protected abstract void informLogChange(Change change);
	/**
	 * Informs the FilesystemService about a file change that occurred. 
	 * @param file - the file that was changed (created/modified/removed)
	 */
	public abstract void newChange(File file);
	/**
	 * Informs the FilesystemService about a folder change that occurred.
	 * @param folder - the folder that was changed (created/removed)
	 */
	public abstract void newChange(Folder folder);
}

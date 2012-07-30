package synchronization;

import topology.TopologyService;
import userIO.UserIO;
import change.Change;

public abstract class SynchronizationService {
	
	SynchronizationStrategy syncStrategy;
	/**
	 * The SynchronizationService needs a list of the online devices and their connectivity information 
	 * in order to synchronize changes. It obtains this list from the TopologyService.
	 */
	TopologyService topologyService;
	
	/**
	 * The SynchronizationService notifies the user about the state of the files 
	 * (finish synchronizing or not, problems that may occur etc)
	 */
	UserIO userIO;
	
	public abstract void synchronizeGroupChange(Change change);
	
	public abstract void synchornizeFilesystemChange(Change change);
}

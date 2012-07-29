package synchronization;

import change.Change;

public abstract class SynchronizationService {
	
	SynchronizationStrategy syncStrategy;
	
	public abstract void synchronizeGroupChange(Change change);
	
	public abstract void synchornizeFilesystemChange(Change change);
}

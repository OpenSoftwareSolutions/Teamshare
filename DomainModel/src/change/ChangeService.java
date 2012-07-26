package change;

import java.util.List;

/**
* Interface for the Change Service, which offers the means for the other components to store and access <b>changes</b>.
*
*/

public abstract class ChangeService {
	
	/**
	 * A reference to the Change Logger, a repository-type component that handles Change objects storage.
	 */
	ChangeLogger changeLogger;
	
	/**
	 * Adds a <b>change</b> to the <b>history of changes</b>
	 * @param change - the Change object, as created by the other components using the ChangeFactory.
	 */
	public abstract void logChange(Change change);
	
	/**
	 * Returns a list of changes that match the given criteria 
	 * (changes of a certain type or for a list of files, changes during a given period, changes for a given group etc).
	 */
	public abstract List<Change> getChanges(ChangeQueryCriteria criteria);
}

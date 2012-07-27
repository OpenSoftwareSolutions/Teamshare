package change;

import java.util.List;

/**
 * Repository for {@link Change} objects. It offers an interface to other modules and components for adding and 
 * retrieving <b>group and filesystem changes<b>.
 */

public abstract class ChangeLogger {

	/**
	 * Adds a change to the history of changes;
	 * @param change - the Change that will be logged
	 */
	public abstract void logChange(Change change);
	
	/**
	 * Returns the changes that match the given criteria, such that it can return all the 
	 * changes in a given period of time for a group or a list of files. If the period is not given in the criteria it will return all the
	 * changes for that given group or files. If the change's type is not given the query will return changes of all types.
	 * @param criteria - the value object that describes the restrictions (if any) for querying the history of changes.  
	 * @return a list of chronologically ordered <b>changes</b>
	 */
	public abstract List<Change> getChanges(ChangeQueryCriteria criteria);
}


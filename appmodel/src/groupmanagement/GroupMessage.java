/**
 * 
 */
package groupmanagement;

import common.Message;

/**
 * @author adriana
 *
 */
public class GroupMessage extends Message{

	private long groupID;

	public long getGroupID() {
		return groupID;
	}

	public void setGroupID(long groupID) {
		this.groupID = groupID;
	}
	
}
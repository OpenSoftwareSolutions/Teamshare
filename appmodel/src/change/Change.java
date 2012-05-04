package change;


import common.User;

/**
 * Class that represents changes in a group.
 * @author adriana
 *
 */
public class Change {
	private String timestamp; // may be changed later to a different format (Date etc)
	private User user;
	private ChangeStatus status;

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the status
	 */
	public ChangeStatus getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(ChangeStatus status) {
		this.status = status;
	}
	
}

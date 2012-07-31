package common;

import java.util.List;

/**
 * Implementation of the User entity representing a Teamshare application user, as "seen" by other users. {@link Account} is used when referring to the
 * user logged in on the device, containing all the user's information. User provides only the information that is needed by other users.
 */
public class User {
	/**
	 * The user's unique identifier. It is not visible through the user interface.
	 * <br>Is the same as the one in the user's Account.
	 */
	public long userID;

	/**
	 * The user's username. It is the one visible to other users through an user interface.
	 * It is also unique.
	 * <br>Is the same as the one in the user's Account.
	 */
	public String username;

	/**
	 * The user's email address, used for receiving notifications. Two users cannot have the same email address.
	 * <br>Is the same as the one in the user's Account.
	 */
	public String email;

	/**
	 * The user's status, which can be seen by other users. True if online (at least one of its devices is online), false otherwise.
	 */
	public boolean online;

	/**
	 * List of all devices linked by the user. This list is created based on information from the <i>remote services</i> and
	 * is not visible to the user. This information is vital for synchronizing changes.
	 */
	public List<Device> devices;
}

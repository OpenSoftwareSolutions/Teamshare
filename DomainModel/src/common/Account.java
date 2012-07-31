package common;

import java.util.List;

import messaging.Mailbox;

/**
 * A Teamshare user. Other users have access/create the {@link User} object of that user, not the Account.
 *
 */
public class Account {
	/**
	 * The user's unique identifier. The identifier is not visible through the user interface.
	 */
	public long userID;
	/**
	 * The user's username. The username the one visible to other users through an user interface.
	 * Similar to the userID, it is unique.
	 */
	public String username;
	/**
	 * The user's email address, the one for receiving notifications. Two users cannot have the same email address.
	 */
	public String email;
	/**
	 * The groups the user is member of.
	 */
	public List<Group> groups;
	/**
	 * The groups the user is owner of (it is a subset of groups).
	 */
	public List<Group> owner;
	/**
	 * The devices the user has linked into the system.
	 */
	public List<Device> devices;
	/**
	 * User's personal information, not having a major role in the application yet so is not defined at this level.
	 */
	public String personalInfo;
	/**
	 * A reference to the user's mailbox, a repository that stores and retrieves the user's messages.
	 */
	public Mailbox mailbox;
}

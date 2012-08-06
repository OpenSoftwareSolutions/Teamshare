package common;

import java.util.List;
import java.util.Properties;

/**
 * The Group entity is defined by an unique ID and its group folder. 
 * In addition it holds attributes such as the group owner, its list of users,
 * security keys and settings.
 *
 */
public class Group {
	/**
	 * The group's unique identifier. It is not visible to users through the application's interface. It is used only internally to identify groups.
	 */
	public long groupID;
	/**
	 * The group folder's name is also the group's actual name.
	 * This folder is the root for all the files and folders created by the group's users.
	 */
	public String folderName;
	/**
	 * The groups owner.
	 */
	public User owner;
	/**
	 * A user that will become the group's owner after an ownership transfer. It is marked as pending only during the transfer.
	 */
	public User pendingOwner;
	/**
	 * For security mechanisms the group must have a series of keys.
	 * Details regarding encryption and authentication techniques are on application level.
	 * At domain level, it is only relevant that they are needed.
	 */
	public List<String> keys;
	/**
	 * The group's users.
	 */
	public List<User> users;
	/**
	 * The group's settings. Settings are implementation dependent. Exact settings or their number is not set at domain level.
	 * They should include properties such as the maximum number of users and the maximum storage capacity.
	 */
	public Properties settings;
}

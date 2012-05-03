package change;

/**
 * Class that represents changes in a group (e.g. a new user etc). (TODO more attributes)
 * @author adriana
 *
 */
public class GroupChange extends Change{
	private GroupChangeType type;

	public GroupChangeType getType() {
		return type;
	}

	public void setType(GroupChangeType type) {
		this.type = type;
	}	
	
}

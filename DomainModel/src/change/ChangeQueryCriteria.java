package change;

import java.io.File;
import java.util.List;

import org.joda.time.DateTime;

import common.Group;

/**
 * Value object representing the properties of a ChangeLog query. 
 * The criteria for the query include the number of files for which to
 * get the changes, the interval in which the changes were made, the
 * group.
 *
 */
public class ChangeQueryCriteria {
	private File file;
	private List<File> files;
	private DateTime from = null;
	private DateTime to = null;
	private Group group;
	private ChangeType changeType = null;

	public ChangeType getChangeType() {
		return changeType;
	}
	public void setChangeType(ChangeType changeType) {
		this.changeType = changeType;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public List<File> getFiles() {
		return files;
	}
	public void setFiles(List<File> files) {
		this.files = files;
	}
	public DateTime getFrom() {
		return from;
	}
	public void setFrom(DateTime from) {
		this.from = from;
	}
	public DateTime getTo() {
		return to;
	}
	public void setTo(DateTime to) {
		this.to = to;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
}

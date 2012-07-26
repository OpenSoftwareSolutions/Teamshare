package change;

import java.io.File;
import java.util.Date;
import java.util.List;

import common.Group;

/**
 * Value object representing the properties of a ChangeLog query. The query can be made for changes of 1 or more files, 
 * during a specific interval, or for all the file and group changes in a group. 
 *
 */
public class ChangeQueryCriteria{
	private File file;
	private List<File> files;
	private Date from = null;
	private Date to = null;
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
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	
}

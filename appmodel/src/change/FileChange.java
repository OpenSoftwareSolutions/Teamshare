package change;

import java.io.File;
import java.util.List;

/**
 * Class that represents changes in a group's files.
 * @author adriana
 *
 */
public class FileChange extends Change {

	private List<File> changedFiles;
	private FileChangeType type;
	public FileChangeType getType() {
		return type;
	}
	public void setType(FileChangeType type) {
		this.type = type;
	}
	/**
	 * @return the changedFiles
	 */
	public List<File> getChangedFiles() {
		return changedFiles;
	}
	/**
	 * @param changedFiles the changedFiles to set
	 */
	public void setChangedFiles(List<File> changedFiles) {
		this.changedFiles = changedFiles;
	}
	
}

package com.oss.teamshare.io;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Event types that can occur for file or folders.
 * @author adriana
 *
 */
enum FileEventType{
	CREATE, DELETE, MODIFY;
}
/**
 * Represents an event related to a file or a folder inside a team's folder.
 *
 */
public class FilesystemEvent {

	protected TeamFile file;
	protected FileEventType eventType;

	public FilesystemEvent(TeamFile file, FileEventType eventType) {
		this.file = file;
		this.eventType = eventType;
	}

	public TeamFile getFile() {
		return file;
	}

	public FileEventType getEventType() {
		return eventType;
	}
}

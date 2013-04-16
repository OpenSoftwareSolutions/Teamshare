package com.oss.teamshare.io;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents an event related to a file or a folder inside a team's folder.
 *
 */
public class FilesystemEvent {

	protected Path file;
	protected FileEventType eventType;

	public FilesystemEvent(Path file, FileEventType eventType) {
		this.file = file;
		this.eventType = eventType;
	}

	public Path getFile() {
		return file;
	}

	public FileEventType getEventType() {
		return eventType;
	}
}

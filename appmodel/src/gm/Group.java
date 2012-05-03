package gm;

import java.io.File;
import java.util.List;

import basic.User;

public class Group {
	private long gid;
	private String folderName;
	private File infoFile;
	private User owner;
	private User pendingOwner;
	private List<User> members;
	private List<User> pendingMembers;
	private String keys; // todo change type
	public long getGid() {
		return gid;
	}
	public void setGid(long gid) {
		this.gid = gid;
	}
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	public File getInfoFile() {
		return infoFile;
	}
	public void setInfoFile(File infoFile) {
		this.infoFile = infoFile;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public User getPendingOwner() {
		return pendingOwner;
	}
	public void setPendingOwner(User pendingOwner) {
		this.pendingOwner = pendingOwner;
	}
	public List<User> getMembers() {
		return members;
	}
	public void setMembers(List<User> members) {
		this.members = members;
	}
	public List<User> getPendingMembers() {
		return pendingMembers;
	}
	public void setPendingMembers(List<User> pendingMembers) {
		this.pendingMembers = pendingMembers;
	}
	public String getKeys() {
		return keys;
	}
	public void setKeys(String keys) {
		this.keys = keys;
	}
	
}

package common;

import java.util.ArrayList;

import messaging.Mailbox;

public class Account {
	public long userID;
	public String username;
	public String email;
	public ArrayList<Group> groups;
	public ArrayList<Device> devices;
	public String personalInfo;
	public Mailbox mailbox;
}
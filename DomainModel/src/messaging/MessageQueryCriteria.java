package messaging;

import java.util.Date;

import common.User;

/**
 * Value object representing the filtering restrictions for querying the Mailbox.
 * It can limit the messages to a time interval, a certain destination, type or status.
 *  The query can be made for changes of 1 or more files, 
 * during a specific interval, or for all the file and group changes in a group. 
 *
 */
public class MessageQueryCriteria {
	private User destination;
	private MessageType messageType;
	private MessageStatus messageStatus;
	private Date from = null;
	private Date to = null;
	
	public User getDestination() {
		return destination;
	}
	public void setDestination(User destination) {
		this.destination = destination;
	}
	public MessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	public MessageStatus getMessageStatus() {
		return messageStatus;
	}
	public void setMessageStatus(MessageStatus messageStatus) {
		this.messageStatus = messageStatus;
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
}

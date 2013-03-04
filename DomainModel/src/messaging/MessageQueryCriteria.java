package messaging;

import org.joda.time.DateTime;

import common.User;

/**
 * Value object representing the filtering restrictions for querying the Mailbox.
 * It can limit the messages to a time interval, a certain destination, type or status.
 * The query can be made for changes of 1 or more files,
 * during a specific interval, or for all the file and group changes in a group.
 *
 */
public class MessageQueryCriteria {
	private User destination;
	private MessageType messageType;
	private MessageStatus messageStatus;
	private DateTime from = null;
	private DateTime to = null;

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
}

package common;

/**
 * Class that represents a Message exchanged between users through the Messaging Service.
 * @author adriana
 *
 */
public class Message {

	private User destination;
	private User source;
	private MessageType type; //nu cred ca mai e nevoie, ca tipul este dat de subclasa mesajul
	private MessageStatus status;
	
	public User getDestination() {
		return destination;
	}
	public void setDestination(User destination) {
		this.destination = destination;
	}
	public User getSource() {
		return source;
	}
	public void setSource(User source) {
		this.source = source;
	}
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	public MessageStatus getStatus() {
		return status;
	}
	public void setStatus(MessageStatus status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	private String content;
	
	
}

package messaging;

public abstract class MessagingService {
	public abstract void receiveMessage(Message message);
	public abstract void sendMessage(Message message);
}

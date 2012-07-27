package messaging;

import group.GroupService;

public abstract class MessagingService {
	GroupService groupService;
	public abstract void receiveMessage(Message message);
	public abstract void sendMessage(Message message);
	/**
	 * Informs the {@link GroupService} about a message it has to process.
	 * @param message
	 */
	protected abstract void informMessage(Message message);
}

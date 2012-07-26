package messaging;

import common.User;

/**
 * Creates Message objects.
 *
 */
public abstract class MessageFactory {
	public abstract Message createMessage(MessageType type, User destination, String content); 
}

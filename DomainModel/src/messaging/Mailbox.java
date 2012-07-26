package messaging;

/**
 * A repository-type element, providing the set of Message objects of an <b>user</b>. 
 * @author adriana
 *
 */
public abstract class Mailbox {
	public abstract void addMessage();
	public abstract void removeMessage();
	public abstract void getMessage();
	
}

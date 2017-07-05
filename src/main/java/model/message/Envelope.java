package model.message;

/**
 * Envelope are used to package all ZeroMQ messages.
 * 
 * @author jonathanhasenburg
 *
 */
public class Envelope{
	
	/**
	 * Used by the subscriber to filter out all messages that are not of interest.
	 */
	public String keygroup = "";
	
	/**
	 * The actual content of a message.
	 */
	public String content = "";

	public Envelope(String keygroup, String content) {
		this.keygroup = keygroup;
		this.content = content;
	}
	
	@Override
	public String toString() {
		return keygroup + " - " + content;
	}
	
}

package model.messages.datarecords;

import model.data.KeygroupID;

/**
 * Envelope are used to package all ZeroMQ messages.
 * 
 * @author jonathanhasenburg
 *
 */
public class Envelope {
	
	/**
	 * ID of the keygroup the content relates to
	 */
	private KeygroupID keygroupID = null;
	
	/**
	 * The message enveloped by the envelope
	 */
	private Message message = null;

	public Envelope(KeygroupID keygroupID, Message message) {
		this.keygroupID = keygroupID;
		this.message = message;
	}
	
	// ************************************************************
	// Generated Code
	// ************************************************************
	
	public KeygroupID getKeygroupID() {
		return keygroupID;
	}

	public void setKeygroupID(KeygroupID keygroupID) {
		this.keygroupID = keygroupID;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((keygroupID == null) ? 0 : keygroupID.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Envelope other = (Envelope) obj;
		if (keygroupID == null) {
			if (other.keygroupID != null)
				return false;
		} else if (!keygroupID.equals(other.keygroupID))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}
	
}

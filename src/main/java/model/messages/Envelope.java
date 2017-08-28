package model.messages;

import model.JSONable;
import model.data.ConfigID;
import model.data.KeygroupID;
import model.data.NodeID;

/**
 * Envelope are used to package all ZeroMQ messages.
 * 
 * @author jonathanhasenburg
 *
 */
public class Envelope implements JSONable {
	
	/**
	 * ID of the keygroup or node the content relates to
	 */
	private ConfigID configID = null;
	
	/**
	 * The message enveloped by the envelope
	 */
	private Message message = null;

	public Envelope(ConfigID configID, Message message) {
		this.configID = configID;
		this.message = message;
	}
	
	public KeygroupID getKeygroupID() {
		if (configID instanceof KeygroupID) {
			KeygroupID keygroupID = (KeygroupID) configID;
			return keygroupID;
		}
		return null;
	}
	
	public void setKeygroupID(KeygroupID keygroupID) {
		this.configID = keygroupID;
	}
	
	public NodeID getNodeID() {
		if (configID instanceof NodeID) {
			NodeID nodeID = (NodeID) configID;
			return nodeID;
		}
		return null;
	}
	
	public void setNodeID(NodeID nodeID) {
		this.configID = nodeID;
	}
	
	// ************************************************************
	// Generated Code
	// ************************************************************
	
	public ConfigID getConfigID() {
		return configID;
	}

	public void setConfigID(ConfigID configID) {
		this.configID = configID;
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
		result = prime * result + ((configID == null) ? 0 : configID.hashCode());
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
		if (configID == null) {
			if (other.configID != null)
				return false;
		} else if (!configID.equals(other.configID))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}
	
}

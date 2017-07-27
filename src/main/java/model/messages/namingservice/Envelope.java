package model.messages.namingservice;

import model.data.NodeID;

public class Envelope {
	
	private NodeID senderID = null;
	
	private Message message = null;
	
	public Envelope(NodeID nodeID, Message message) {
		this.senderID = nodeID;
		this.message = message;
	}

	public NodeID getNodeID() {
		return senderID;
	}

	public void setNodeID(NodeID nodeID) {
		this.senderID = nodeID;
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
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((senderID == null) ? 0 : senderID.hashCode());
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
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (senderID == null) {
			if (other.senderID != null)
				return false;
		} else if (!senderID.equals(other.senderID))
			return false;
		return true;
	}
}

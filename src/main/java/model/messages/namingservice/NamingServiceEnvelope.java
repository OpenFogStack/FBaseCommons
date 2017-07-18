package model.messages.namingservice;

import model.data.NodeID;

public class NamingServiceEnvelope {
	
	private NodeID nodeID = null;
	
	private NamingServiceMessage message = null;
	
	public NamingServiceEnvelope(NodeID nodeID, NamingServiceMessage message) {
		this.nodeID = nodeID;
		this.message = message;
	}

	public NodeID getNodeID() {
		return nodeID;
	}

	public void setNodeID(NodeID nodeID) {
		this.nodeID = nodeID;
	}

	public NamingServiceMessage getMessage() {
		return message;
	}

	public void setMessage(NamingServiceMessage message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((nodeID == null) ? 0 : nodeID.hashCode());
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
		NamingServiceEnvelope other = (NamingServiceEnvelope) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (nodeID == null) {
			if (other.nodeID != null)
				return false;
		} else if (!nodeID.equals(other.nodeID))
			return false;
		return true;
	}
}

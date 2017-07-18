package model.config;

import model.data.NodeID;

/**
 * 
 * @author jonathanhasenburg
 *
 */
public class TriggerNodeConfig extends KeygroupMember {

	private String nodeID = null;
    
    public TriggerNodeConfig() {
     
    }

	public TriggerNodeConfig(String nodeID) {
		this.nodeID = nodeID;
	}

	// ************************************************************
	// Generated Code
	// ************************************************************
	
	public NodeID getNodeID() {
		return (NodeID) id;
	}

	public void setNodeID(NodeID nodeID) {
		this.id = nodeID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		TriggerNodeConfig other = (TriggerNodeConfig) obj;
		if (nodeID == null) {
			if (other.nodeID != null)
				return false;
		} else if (!nodeID.equals(other.nodeID))
			return false;
		return true;
	}
	
}

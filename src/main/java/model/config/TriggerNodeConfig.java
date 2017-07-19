package model.config;

import model.data.NodeID;

/**
 * Config object for trigger nodes in the Keygroup.
 * 
 * @author jonathanhasenburg
 *
 */
public class TriggerNodeConfig extends KeygroupMember {

	/**
	 * The ID of the node
	 */
	private NodeID nodeID = null;
    
	/**
	 * Empty constructor used for JSON parsing
	 */
    public TriggerNodeConfig() {
     
    }

    /**
     * Main constructor for creating a new TriggerNodeConfig
     * 
     * @param nodeID The ID of the node
     */
	public TriggerNodeConfig(NodeID nodeID) {
		this.nodeID = nodeID;
	}
	
	@Override
	public NodeID getID() {
		return getNodeID();
	}

	// ************************************************************
	// Generated Code
	// ************************************************************
	
	public NodeID getNodeID() {
		return nodeID;
	}

	public void setNodeID(NodeID nodeID) {
		this.nodeID = nodeID;
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

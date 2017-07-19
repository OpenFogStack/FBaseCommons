package model.config;

import model.data.NodeID;

/**
 * Config object for replica nodes in the Keygroup.
 * 
 * @author jonathanhasenburg
 *
 */
public class ReplicaNodeConfig extends KeygroupMember {

	/**
	 * Time for data to live on the node in seconds
	 */
	private Integer timeToLive = null;
	
	/**
	 * The ID of the node
	 */
	private NodeID nodeID = null;
    
	/**
	 * Empty constructor used for JSON parsing
	 */
    public ReplicaNodeConfig() {
    
    }

    /**
     * Main constructor for creating a new ReplicaNodeConfig
     * 
     * @param nodeID The ID of the node
     * @param timeToLive Time for the data to live on the node in seconds
     */
	public ReplicaNodeConfig(NodeID nodeID, Integer timeToLive) {
		this.nodeID = nodeID;
		this.timeToLive = timeToLive;
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

	public Integer getTimeToLive() {
		return timeToLive;
	}

	public void setTimeToLive(Integer timeToLive) {
		this.timeToLive = timeToLive;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((timeToLive == null) ? 0 : timeToLive.hashCode());
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
		ReplicaNodeConfig other = (ReplicaNodeConfig) obj;
		if (timeToLive == null) {
			if (other.timeToLive != null)
				return false;
		} else if (!timeToLive.equals(other.timeToLive))
			return false;
		return true;
	}
	
}

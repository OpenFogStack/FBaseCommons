package model.config;

import model.data.NodeID;

/**
 * 
 * @author jonathanhasenburg
 *
 */
public class ReplicaNodeConfig extends KeygroupMember {

	private Integer timeToLive = null;
    
    public ReplicaNodeConfig() {
    
    }

	public ReplicaNodeConfig(NodeID nodeID, Integer timeToLive) {
		this.id = nodeID;
		this.timeToLive = timeToLive;
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

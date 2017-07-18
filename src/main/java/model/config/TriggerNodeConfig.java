package model.config;

import model.data.NodeID;

/**
 * 
 * @author jonathanhasenburg
 *
 */
public class TriggerNodeConfig extends KeygroupMember {

    private String endpoint = null;
    
    public TriggerNodeConfig() {
     
    }

	public TriggerNodeConfig(NodeID nodeID, String endpoint) {
		this.id = nodeID;
		this.endpoint = endpoint;
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

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endpoint == null) ? 0 : endpoint.hashCode());
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
		if (endpoint == null) {
			if (other.endpoint != null)
				return false;
		} else if (!endpoint.equals(other.endpoint))
			return false;
		return true;
	}
	
}

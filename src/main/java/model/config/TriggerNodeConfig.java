package model.config;

/**
 * 
 * @author jonathanhasenburg
 *
 */
public class TriggerNodeConfig extends KeygroupMember {

	private String nodeID = null;
    private String endpoint = null;
    
    public TriggerNodeConfig() {
     
    }

	public TriggerNodeConfig(String nodeID, String endpoint) {
		this.nodeID = nodeID;
		this.endpoint = endpoint;
	}

	// ************************************************************
	// Generated Code
	// ************************************************************
	
	public String getNodeID() {
		return nodeID;
	}

	public void setNodeID(String nodeID) {
		this.nodeID = nodeID;
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
		if (endpoint == null) {
			if (other.endpoint != null)
				return false;
		} else if (!endpoint.equals(other.endpoint))
			return false;
		if (nodeID == null) {
			if (other.nodeID != null)
				return false;
		} else if (!nodeID.equals(other.nodeID))
			return false;
		return true;
	}
	
}

package model.config;

/**
 * 
 * @author jonathanhasenburg
 *
 */
public class ReplicaNodeConfig extends KeygroupMember {

	private String nodeID = null;
    private String endpoint = null;
    private Integer timeToLive = null;
    
    public ReplicaNodeConfig() {
    
    }

	public ReplicaNodeConfig(String nodeID, String endpoint, Integer timeToLive) {
		this.nodeID = nodeID;
		this.endpoint = endpoint;
		this.timeToLive = timeToLive;
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
		result = prime * result + ((endpoint == null) ? 0 : endpoint.hashCode());
		result = prime * result + ((nodeID == null) ? 0 : nodeID.hashCode());
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
		if (timeToLive == null) {
			if (other.timeToLive != null)
				return false;
		} else if (!timeToLive.equals(other.timeToLive))
			return false;
		return true;
	}
	
}

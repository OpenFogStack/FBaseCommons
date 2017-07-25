package model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class to hold the ID of a logical node in the system
 * 
 * @author Wm. Keith van der Meulen
 *
 */
public class NodeID extends ConfigID {

	/**
	 * The unique ID of the node
	 */
	private String nodeID = null;
	
	/**
	 * Empty constructor used for JSON parsing
	 */
	public NodeID() {
		
	}
	
	/**
	 * Main constructor for creating a NodeID
	 * 
	 * @param nodeID The unique ID of the node
	 */
	public NodeID(String nodeID) {
		this.nodeID = nodeID;
	}
	
	@Override
	@JsonIgnore
	public String getID() {
		return getNodeID();
	}

	public String getNodeID() {
		return nodeID;
	}

	public void setNodeID(String nodeID) {
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
		NodeID other = (NodeID) obj;
		if (nodeID == null) {
			if (other.nodeID != null)
				return false;
		} else if (!nodeID.equals(other.nodeID))
			return false;
		return true;
	}
	
}

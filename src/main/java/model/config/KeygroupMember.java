package model.config;

import com.fasterxml.jackson.annotation.JsonIgnore;

import model.data.NodeID;

/**
 * Parent config object for member nodes in a keygroup
 * 
 * @author Wm. Keith van der Meulen
 *
 */
public abstract class KeygroupMember extends Config {
	/**
	 * The ID of the node
	 */
	protected NodeID nodeID = null;
	
	public NodeID getNodeID() {
		return nodeID;
	}

	public void setNodeID(NodeID nodeID) {
		this.nodeID = nodeID;
	}
	
	@Override
	@JsonIgnore
	public NodeID getID() {
		return getNodeID();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nodeID == null) ? 0 : nodeID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeygroupMember other = (KeygroupMember) obj;
		if (nodeID == null) {
			if (other.nodeID != null)
				return false;
		} else if (!nodeID.equals(other.nodeID))
			return false;
		return true;
	}
}

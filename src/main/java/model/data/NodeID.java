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
}

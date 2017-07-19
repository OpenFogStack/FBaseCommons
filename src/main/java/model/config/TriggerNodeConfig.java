package model.config;

import model.data.NodeID;

/**
 * 
 * @author jonathanhasenburg
 *
 */
public class TriggerNodeConfig extends KeygroupMember {
    
    public TriggerNodeConfig() {
     
    }

	public TriggerNodeConfig(NodeID nodeID) {
		this.id = nodeID;
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
	
}

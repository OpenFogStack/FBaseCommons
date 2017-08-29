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
}

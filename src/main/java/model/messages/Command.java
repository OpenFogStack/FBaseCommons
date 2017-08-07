package model.messages;

public enum Command {
	CLIENT_CONFIG_CREATE,
	CLIENT_CONFIG_READ,
	CLIENT_CONFIG_UPDATE,
	CLIENT_CONFIG_DELETE,
	
	NODE_CONFIG_CREATE,
	NODE_CONFIG_READ,
	NODE_CONFIG_UPDATE,
	NODE_CONFIG_DELETE,
	
	KEYGROUP_CONFIG_CREATE,
	KEYGROUP_CONFIG_ADD_REPLICA_NODE,
	KEYGROUP_CONFIG_ADD_TRIGGER_NODE,
	KEYGROUP_CONFIG_READ,
	KEYGROUP_CONFIG_UPDATE_CRYPTO,
	KEYGROUP_CONFIG_DELETE,
	
	PUT_DATA_RECORD,
	DELETE_DATA_RECORD;
}

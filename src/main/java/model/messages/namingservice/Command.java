package model.messages.namingservice;

public enum Command {
	CLIENT_CREATE,
	CLIENT_READ,
	CLIENT_UPDATE,
	CLIENT_DELETE,
	
	NODE_CREATE,
	NODE_READ,
	NODE_UPDATE,
	NODE_DELETE,
	
	KEYGROUP_CREATE,
	KEYGROUP_ADD_REPLICA_NODE,
	KEYGROUP_ADD_TRIGGER_NODE,
	KEYGROUP_READ,
	KEYGROUP_UPDATE_CRYPTO,
	KEYGROUP_DELETE;
}

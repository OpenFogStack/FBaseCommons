package model.messages.namingservice;

public enum NamingServiceCommand {
	ClientCreate,
	ClientRead,
	ClientUpdate,
	ClientDelete,
	
	NodeCreate,
	NodeRead,
	NodeUpdate,
	NodeDelete,
	
	KeygroupCreate,
	KeygroupAddReplicaNode,
	KeygroupAddTriggerNode,
	KeygroupRead,
	KeygroupUpdateCrypto,
	KeygroupDelete;
}

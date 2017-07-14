package model.message.namespace;

public enum NamespaceCommand {
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

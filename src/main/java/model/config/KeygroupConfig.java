package model.config;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import crypto.CryptoProvider.EncryptionAlgorithm;
import model.data.DataRecord;
import model.data.KeygroupID;
import model.data.NodeID;

/**
 * Configuration class for keygroups. Keygroups acts as a logical grouping unit for {@link DataRecord} objects.
 * They also control read and write privileges, and replication.
 * 
 * @author jonathanhasenburg
 *
 */
public class KeygroupConfig extends Config {
	
	/**
	 * ID of the keygroup
	 */
	private KeygroupID keygroupID = null;
	
	/**
	 * Clients that have access to data stored within a keygroup.
	 */
	private Set<String> clients = new HashSet<String>();
	
	/**
	 * List of fog nodes that receive and replica data.
	 */
	private Set<ReplicaNodeConfig> replicaNodeConfigs = new HashSet<ReplicaNodeConfig>();
	
	/**
	 * List of fog nodes that only receive data.
	 */
	private Set<TriggerNodeConfig> triggerNodeConfigs = new HashSet<TriggerNodeConfig>();
	
	/**
	 * Encryption secret (symmetric) for communication within a keygroup.
	 */
	private String encryptionSecret = "";
	
	/**
	 * Encryption algorithm (symmetric) used for communication within a keygroup.
	 */
	private EncryptionAlgorithm encryptionAlgorithm = null;
	
	/**
	 * Empty constructor used for JSON parsing
	 */
	public KeygroupConfig() {
		
	}
	
	/**
	 * Constructor for new empty keygroup.
	 * 
	 * @param keygroupID The unique ID of the keygroup
	 * @param encryptionSecret The symmetric encryption secret used in internal keygroup communication
	 * @param encryptionAlgorithm The symmetric encryption algorithm used in internal keygroup communication
	 */
	public KeygroupConfig(KeygroupID keygroupID, String encryptionSecret, EncryptionAlgorithm encryptionAlgorithm) {
		this.keygroupID = keygroupID;
		this.encryptionSecret = encryptionSecret;
		this.encryptionAlgorithm = encryptionAlgorithm;
	}
	
	@Override
	@JsonIgnore
	public KeygroupID getID() {
		return getKeygroupID();
	}

	public KeygroupID getKeygroupID() {
		return keygroupID;
	}

	public void setKeygroupID(KeygroupID keygroupID) {
		this.keygroupID = keygroupID;
	}

	public Set<String> getClients() {
		return clients;
	}

	public void setClients(Set<String> clients) {
		this.clients = clients;
	}
	
	public boolean addClient(String client) {
		return clients.add(client);
	}
	
	public boolean removeClient(String client) {
		return clients.remove(client);
	}

	public Set<ReplicaNodeConfig> getReplicaNodes() {
		return replicaNodeConfigs;
	}

	public void setReplicaNodes(Set<ReplicaNodeConfig> replicaNodeConfigs) {
		this.replicaNodeConfigs = replicaNodeConfigs;
	}
	
	public boolean addReplicaNode(ReplicaNodeConfig replicaNodeConfig) {
		return replicaNodeConfigs.add(replicaNodeConfig);
	}
	
	public boolean removeReplicaNode(NodeID nodeID) {
		for(ReplicaNodeConfig r : replicaNodeConfigs) {
			if(r.getNodeID() == nodeID) {
				return replicaNodeConfigs.remove(r);
			}
		}
		
		return false;
	}
	
	public boolean containsReplicaNode(NodeID nodeID) {
		for(ReplicaNodeConfig r : replicaNodeConfigs) {
			if(r.getNodeID() == nodeID) {
				return true;
			}
		}
		
		return false;
	}

	public Set<TriggerNodeConfig> getTriggerNodes() {
		return triggerNodeConfigs;
	}

	public void setTriggerNodes(Set<TriggerNodeConfig> triggerNodeConfigs) {
		this.triggerNodeConfigs = triggerNodeConfigs;
	}
	
	public boolean addTriggerNode(TriggerNodeConfig triggerNodeConfig) {
		return triggerNodeConfigs.add(triggerNodeConfig);
	}
	
	public boolean removeTriggerNode(NodeID nodeID) {
		for(TriggerNodeConfig t : triggerNodeConfigs) {
			if(t.getNodeID() == nodeID) {
				return triggerNodeConfigs.remove(t);
			}
		}
		
		return false;
	}
	
	public boolean containsTriggerNode(NodeID nodeID) {
		for(TriggerNodeConfig t : triggerNodeConfigs) {
			if(t.getNodeID() == nodeID) {
				return true;
			}
		}
		
		return false;
	}

	public String getEncryptionSecret() {
		return encryptionSecret;
	}

	public void setEncryptionSecret(String encryptionSecret) {
		this.encryptionSecret = encryptionSecret;
	}

	public EncryptionAlgorithm getEncryptionAlgorithm() {
		return encryptionAlgorithm;
	}

	public void setEncryptionAlgorithm(EncryptionAlgorithm encryptionAlgorithm) {
		this.encryptionAlgorithm = encryptionAlgorithm;
	}

	// ************************************************************
	// Generated Code
	// ************************************************************

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((clients == null) ? 0 : clients.hashCode());
		result = prime * result + ((encryptionAlgorithm == null) ? 0 : encryptionAlgorithm.hashCode());
		result = prime * result + ((encryptionSecret == null) ? 0 : encryptionSecret.hashCode());
		result = prime * result + ((replicaNodeConfigs == null) ? 0 : replicaNodeConfigs.hashCode());
		result = prime * result + ((triggerNodeConfigs == null) ? 0 : triggerNodeConfigs.hashCode());
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
		KeygroupConfig other = (KeygroupConfig) obj;
		if (clients == null) {
			if (other.clients != null)
				return false;
		} else if (!clients.equals(other.clients))
			return false;
		if (encryptionAlgorithm != other.encryptionAlgorithm)
			return false;
		if (encryptionSecret == null) {
			if (other.encryptionSecret != null)
				return false;
		} else if (!encryptionSecret.equals(other.encryptionSecret))
			return false;
		if (replicaNodeConfigs == null) {
			if (other.replicaNodeConfigs != null)
				return false;
		} else if (!replicaNodeConfigs.equals(other.replicaNodeConfigs))
			return false;
		if (triggerNodeConfigs == null) {
			if (other.triggerNodeConfigs != null)
				return false;
		} else if (!triggerNodeConfigs.equals(other.triggerNodeConfigs))
			return false;
		return true;
	}
	
}

package model.config;

import java.util.List;

import crypto.CryptoProvider.EncryptionAlgorithm;
import model.data.DataRecord;
import model.data.KeygroupID;

/**
 * Configuration class for keygroups. Keygroups acts as a logical grouping unit for {@link DataRecord} objects.
 * They also control read and write privileges, and replication.
 * 
 * @author jonathanhasenburg
 *
 */
public class KeygroupConfig {

	/**
	 * ID of the keygroup.
	 */
	private KeygroupID keygroupID = null;
	
	/**
	 * Clients that have access to data stored within a keygroup.
	 */
	private List<String> clients = null;
	
	/**
	 * List of fog nodes that receive and replica data.
	 */
	private List<ReplicaNodeConfig> replicaNodeConfigs = null;
	
	/**
	 * List of fog nodes that only receive data.
	 */
	private List<TriggerNodeConfig> triggerNodeConfigs = null;
	
	/**
	 * Encryption secret (symmetric) for communication within a keygroup.
	 */
	private String encryptionSecret = "";
	
	/**
	 * Encryption algorithm (symmetric) used for communication within a keygroup.
	 */
	private EncryptionAlgorithm encryptionAlgorithm = null;
	
	public KeygroupConfig() {
		
	}
	
	public KeygroupConfig(KeygroupID keygroupID, String encryptionSecret, 
			EncryptionAlgorithm encryptionAlgorithm) {
		this.keygroupID = keygroupID;
		this.encryptionSecret = encryptionSecret;
		this.encryptionAlgorithm = encryptionAlgorithm;
	}

	// ************************************************************
	// Generated Code
	// ************************************************************
	
	public KeygroupID getKeygroupID() {
		return keygroupID;
	}

	public void setKeygroupID(KeygroupID keygroupID) {
		this.keygroupID = keygroupID;
	}

	public List<String> getClients() {
		return clients;
	}

	public void setClients(List<String> clients) {
		this.clients = clients;
	}

	public List<ReplicaNodeConfig> getReplicaNodes() {
		return replicaNodeConfigs;
	}

	public void setReplicaNodes(List<ReplicaNodeConfig> replicaNodeConfigs) {
		this.replicaNodeConfigs = replicaNodeConfigs;
	}

	public List<TriggerNodeConfig> getTriggerNodes() {
		return triggerNodeConfigs;
	}

	public void setTriggerNodes(List<TriggerNodeConfig> triggerNodeConfigs) {
		this.triggerNodeConfigs = triggerNodeConfigs;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clients == null) ? 0 : clients.hashCode());
		result = prime * result + ((encryptionAlgorithm == null) ? 0 : encryptionAlgorithm.hashCode());
		result = prime * result + ((encryptionSecret == null) ? 0 : encryptionSecret.hashCode());
		result = prime * result + ((keygroupID == null) ? 0 : keygroupID.hashCode());
		result = prime * result + ((replicaNodeConfigs == null) ? 0 : replicaNodeConfigs.hashCode());
		result = prime * result + ((triggerNodeConfigs == null) ? 0 : triggerNodeConfigs.hashCode());
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
		if (keygroupID == null) {
			if (other.keygroupID != null)
				return false;
		} else if (!keygroupID.equals(other.keygroupID))
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

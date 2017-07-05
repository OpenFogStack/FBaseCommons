package model.config;

import java.util.List;

import crypto.CryptoProvider.EncryptionAlgorithm;
import model.Entity;

/**
 * 
 * @author jonathanhasenburg
 *
 */
public class NodeConfig extends Entity {

	private String nodeID = null;
    private String publicKey = null;
    private EncryptionAlgorithm encryptionAlgorithm = null;
    private List<String> machines = null;
    private Integer publisherPort = null;
    private Integer messagePort = null;
    private Integer restPort = null;
    private String location = null;
    private String description = null;
    
    public NodeConfig() {
    	
    }
    
	// ************************************************************
	// Generated Code
	// ************************************************************

	public String getNodeID() {
		return nodeID;
	}

	public void setNodeID(String nodeID) {
		this.nodeID = nodeID;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public EncryptionAlgorithm getEncryptionAlgorithm() {
		return encryptionAlgorithm;
	}

	public void setEncryptionAlgorithm(EncryptionAlgorithm encryptionAlgorithm) {
		this.encryptionAlgorithm = encryptionAlgorithm;
	}

	public List<String> getMachines() {
		return machines;
	}

	public void setMachines(List<String> machines) {
		this.machines = machines;
	}

	public Integer getPublisherPort() {
		return publisherPort;
	}

	public void setPublisherPort(Integer publisherPort) {
		this.publisherPort = publisherPort;
	}

	public Integer getMessagePort() {
		return messagePort;
	}

	public void setMessagePort(Integer messagePort) {
		this.messagePort = messagePort;
	}

	public Integer getRestPort() {
		return restPort;
	}

	public void setRestPort(Integer restPort) {
		this.restPort = restPort;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((encryptionAlgorithm == null) ? 0 : encryptionAlgorithm.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((machines == null) ? 0 : machines.hashCode());
		result = prime * result + ((messagePort == null) ? 0 : messagePort.hashCode());
		result = prime * result + ((nodeID == null) ? 0 : nodeID.hashCode());
		result = prime * result + ((publicKey == null) ? 0 : publicKey.hashCode());
		result = prime * result + ((publisherPort == null) ? 0 : publisherPort.hashCode());
		result = prime * result + ((restPort == null) ? 0 : restPort.hashCode());
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
		NodeConfig other = (NodeConfig) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (encryptionAlgorithm != other.encryptionAlgorithm)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (machines == null) {
			if (other.machines != null)
				return false;
		} else if (!machines.equals(other.machines))
			return false;
		if (messagePort == null) {
			if (other.messagePort != null)
				return false;
		} else if (!messagePort.equals(other.messagePort))
			return false;
		if (nodeID == null) {
			if (other.nodeID != null)
				return false;
		} else if (!nodeID.equals(other.nodeID))
			return false;
		if (publicKey == null) {
			if (other.publicKey != null)
				return false;
		} else if (!publicKey.equals(other.publicKey))
			return false;
		if (publisherPort == null) {
			if (other.publisherPort != null)
				return false;
		} else if (!publisherPort.equals(other.publisherPort))
			return false;
		if (restPort == null) {
			if (other.restPort != null)
				return false;
		} else if (!restPort.equals(other.restPort))
			return false;
		return true;
	}
    
}

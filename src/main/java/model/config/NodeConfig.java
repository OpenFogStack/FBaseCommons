package model.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import crypto.CryptoProvider.EncryptionAlgorithm;
import model.JSONable;
import model.data.NodeID;

/**
 * Config object representing a logical node in the system
 * 
 * @author jonathanhasenburg
 *
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class NodeConfig extends Config {

	/**
	 * The unique ID for the node
	 */
	private NodeID nodeID = null;
	
	/**
	 * The public key for the node
	 */
	private String publicKey = null;
	
	/**
	 * The encryption algorithm for the node
	 */
    private EncryptionAlgorithm encryptionAlgorithm = null;
    
    /**
     * List of machine addresses that make up the logical node
     */
    private List<String> machines = null;
    
    /**
     * Publisher port for the node
     */
    private Integer publisherPort = null;
    
    /**
     * Message port for the node
     */
    private Integer messagePort = null;
    
    /**
     * Rest port for the node
     */
    private Integer restPort = null;
    
    /**
     * Plain text location of the node
     */
    private String location = null;
    
    /**
     * Plain text description of the node
     */
    private String description = null;
    
    /**
	 * Empty constructor used for JSON parsing
	 */
    public NodeConfig() {
    	
    }
    
    /**
     * Main constructor for creating a new node
     * 
	 * @param nodeID The unique ID for the node
	 * @param publicKey The public key for the node
	 * @param encryptionAlgorithm The encryption algorithm for the node
	 * @param machines List of machine addresses that make up the logical node
	 * @param publisherPort Publisher port for the node
	 * @param messagePort Message port for the node
	 * @param restPort Rest port for the node
	 * @param location Plain text location of the node
	 * @param description Plain text description of the node
	 */
	public NodeConfig(NodeID nodeID, String publicKey, EncryptionAlgorithm encryptionAlgorithm, List<String> machines,
			Integer publisherPort, Integer messagePort, Integer restPort, String location, String description) {
		this.nodeID = nodeID;
		this.publicKey = publicKey;
		this.encryptionAlgorithm = encryptionAlgorithm;
		this.machines = machines;
		this.publisherPort = publisherPort;
		this.messagePort = messagePort;
		this.restPort = restPort;
		this.location = location;
		this.description = description;
	}

	@Override
	@JsonIgnore
    public NodeID getID() {
    	return getNodeID();
    }
	
	public void addMachine(String machineIP) {
		if (machines == null) {
			this.machines = new ArrayList<>();
		}
		machines.add(machineIP);
	}
    
	// ************************************************************
	// Generated Code
	// ************************************************************

	public NodeID getNodeID() {
		return nodeID;
	}

	public void setNodeID(NodeID nodeID) {
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		NodeConfig that = (NodeConfig) o;
		return Objects.equals(getNodeID(), that.getNodeID()) &&
				Objects.equals(getPublicKey(), that.getPublicKey()) &&
				getEncryptionAlgorithm() == that.getEncryptionAlgorithm() &&
				Objects.equals(getMachines(), that.getMachines()) &&
				Objects.equals(getPublisherPort(), that.getPublisherPort()) &&
				Objects.equals(getMessagePort(), that.getMessagePort()) &&
				Objects.equals(getRestPort(), that.getRestPort()) &&
				Objects.equals(getLocation(), that.getLocation()) &&
				Objects.equals(getDescription(), that.getDescription());
	}

	@Override
	public int hashCode() {

		return Objects.hash(super.hashCode(), getNodeID(), getPublicKey(), getEncryptionAlgorithm(), getMachines(), getPublisherPort(), getMessagePort(), getRestPort(), getLocation(), getDescription());
	}

	@Override
	public String toString() {
		return JSONable.toJSON(this);
	}
}

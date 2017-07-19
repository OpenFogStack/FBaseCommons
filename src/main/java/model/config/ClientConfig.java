package model.config;

import com.fasterxml.jackson.annotation.JsonIgnore;

import crypto.CryptoProvider.EncryptionAlgorithm;
import model.data.ClientID;

/**
 * Config object representing a client in the system
 * 
 * @author jonathanhasenburg
 *
 */
public class ClientConfig extends Config {

	/**
	 * The unique ID of the client
	 */
	private ClientID clientID = null;
	
	/**
	 * The public key for the client
	 */
    private String publicKey = null;
    
    /**
     * The encryption algorithm for the client
     */
    private EncryptionAlgorithm encryptionAlgorithm = null;
    
    /**
	 * Empty constructor used for JSON parsing
	 */
    public ClientConfig() {
    	
    }
    
    /**
     * Main constructor for creating a new client
     * 
	 * @param clientID The unique ID of the client
	 * @param publicKey The public key for the client
	 * @param encryptionAlgorithm The encryption algorithm for the client
	 */
	public ClientConfig(ClientID clientID, String publicKey, EncryptionAlgorithm encryptionAlgorithm) {
		this.clientID = clientID;
		this.publicKey = publicKey;
		this.encryptionAlgorithm = encryptionAlgorithm;
	}

	@Override
	@JsonIgnore
    public ClientID getID() {
    	return getClientID();
    }

	// ************************************************************
	// Generated Code
	// ************************************************************
    
	public ClientID getClientID() {
		return clientID;
	}

	public void setClientID(ClientID clientID) {
		this.clientID = clientID;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clientID == null) ? 0 : clientID.hashCode());
		result = prime * result + ((encryptionAlgorithm == null) ? 0 : encryptionAlgorithm.hashCode());
		result = prime * result + ((publicKey == null) ? 0 : publicKey.hashCode());
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
		ClientConfig other = (ClientConfig) obj;
		if (clientID == null) {
			if (other.clientID != null)
				return false;
		} else if (!clientID.equals(other.clientID))
			return false;
		if (encryptionAlgorithm != other.encryptionAlgorithm)
			return false;
		if (publicKey == null) {
			if (other.publicKey != null)
				return false;
		} else if (!publicKey.equals(other.publicKey))
			return false;
		return true;
	}
	
}

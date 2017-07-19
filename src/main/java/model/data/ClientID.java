package model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Class to hold the ID of a client in the system
 * 
 * @author Wm. Keith van der Meulen
 *
 */
public class ClientID extends ConfigID {
	
	/**
	 * The unique ID of the client
	 */
	private String clientID = null;
	
	/**
	 * Empty constructor used for JSON parsing
	 */
	public ClientID() {
		
	}
	
	/**
	 * Main constructor for creating a ClientID
	 * 
	 * @param clientID The unique ID of the client
	 */
	public ClientID(String clientID) {
		this.clientID = clientID;
	}
	
	@Override
	@JsonIgnore
	public String getID() {
		return getClientID();
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clientID == null) ? 0 : clientID.hashCode());
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
		ClientID other = (ClientID) obj;
		if (clientID == null) {
			if (other.clientID != null)
				return false;
		} else if (!clientID.equals(other.clientID))
			return false;
		return true;
	}
	
}

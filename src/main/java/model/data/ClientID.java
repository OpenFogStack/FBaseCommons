package model.data;

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
	public String getID() {
		return getClientID();
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
}

package model.data;

import model.JSONable;

/**
 * General class for ID's used by system configs
 * 
 * @author Wm. Keith van der Meulen
 *
 */
public abstract class ConfigID implements JSONable {
	
	/**
	 * Method to return a String representation of the ID
	 * 
	 * @return Returns a String representation of the ID
	 */
	public abstract String getID();
}

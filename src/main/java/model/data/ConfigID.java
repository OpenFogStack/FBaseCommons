package model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
	public abstract String getID();
	
	@Override
	public String toString() {
		return getID();
	}
}

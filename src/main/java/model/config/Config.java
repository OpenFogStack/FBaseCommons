package model.config;

import com.fasterxml.jackson.annotation.JsonIgnore;

import model.JSONable;
import model.data.ConfigID;

/**
 * Base class for all model classes that require JSON serialization.
 * 
 * @author jonathanhasenburg
 *
 */
public abstract class Config implements JSONable {
	
	/**
	 * Method to return the ID of any config object
	 * 
	 * @return the ID of the config object
	 */
	@JsonIgnore
	abstract ConfigID getID();
	
	// TODO add utility methods to split host:port into parts (and to create a string using both)

}

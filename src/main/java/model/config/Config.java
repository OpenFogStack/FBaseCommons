package model.config;

import model.JSONable;
import model.data.ConfigID;

/**
 * 
 * Base class for all model classes that require JSON serialization.
 * 
 * @author jonathanhasenburg
 *
 */
public abstract class Config implements JSONable {
	
	abstract ConfigID getID();
	
	// TODO add utility methods to split host:port into parts (and to create a string using both)

}

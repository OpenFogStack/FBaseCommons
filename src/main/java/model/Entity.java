package model;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 
 * Base class for all model classes that require JSON serialization.
 * 
 * @author jonathanhasenburg
 *
 */
public abstract class Entity {

	private static Logger logger = Logger.getLogger(Entity.class.getName());

	public static <T> T createFromJSON(String json, Class<T> targetClass) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, targetClass);
		} catch (Exception e) {
			logger.error("Could not translate json to " + targetClass.getName());
			logger.error(e.getMessage());
			return null;
		}
	}

	public String toJSON() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String json = null;
		try {
			json = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	// TODO add utility methods to split host:port into parts (and to create a string using both)
	
}

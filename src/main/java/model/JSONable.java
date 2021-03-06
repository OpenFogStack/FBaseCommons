package model;

import java.io.InputStream;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public interface JSONable {

	public static Logger logger = Logger.getLogger(JSONable.class.getName());

	public static <T> T fromJSON(String json, Class<T> targetClass) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, targetClass);
		} catch (Exception e) {
			logger.error("Could not translate json to " + targetClass.getName());
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public static <T> T fromJSON(InputStream stream, Class<T> targetClass) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(stream, targetClass);
		} catch (Exception e) {
			logger.error("Could not translate json to " + targetClass.getName());
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public static <T> T fromJSON(String json, TypeReference<T> targetClass) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, targetClass);
		} catch (Exception e) {
			logger.error("Could not translate json to " + targetClass.getClass());
			logger.error(e.getMessage());
			return null;
		}
	}

	public static String toJSON(JSONable obj) {
		// Only include non-null field
		ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(Include.NON_NULL);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String json = null;
		try {
			json = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T clone(JSONable object) {
		if (object == null) {
			return null;
		}
		
		String json = toJSON(object);
		JSONable clone = fromJSON(json, object.getClass());
		
		return (T) clone;
	}
	
}

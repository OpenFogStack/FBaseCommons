package model;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Keygroups are used to describe a {@link DataRecord}.
 * 
 * @author jonathanhasenburg
 *
 */
public class Keygroup {

	private static Logger logger = Logger.getLogger(Keygroup.class.getName());
	
	/**
	 * An identifier for the belonging application.
	 */
	public String app;
	
	/**
	 * An identifier for the origin of the data record. Typically the creator.
	 */
	public String originator;
	
	/**
	 * An identifier that describes the data record, e.g. brightness.
	 */
	public String descriptor;
	
	public Keygroup() {
		
	}
	
	public Keygroup(String app, String originator, String descriptor) {
		this.app = app;
		this.originator = originator;
		this.descriptor = descriptor;
	}

	public static Keygroup createFromJSON(String json) 
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, Keygroup.class);
		} catch (Exception e) {
			logger.error("Could not translate json to GetRequest.");
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
	
	public static Keygroup createFromString(String string) {
		if (string == null) {
			return null;
		}
		String[] split = string.split("\\.");
		if (split.length == 3) {
			return new Keygroup(split[0], split[1], split[2]);
		}
		return null;
	}
	
	@Override
	public String toString() {
		return app + "." + originator + "." + descriptor;
	}
		
	// ************************************************************
	// Generated Code
	// ************************************************************

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getOriginator() {
		return originator;
	}

	public void setOriginator(String originator) {
		this.originator = originator;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((app == null) ? 0 : app.hashCode());
		result = prime * result + ((descriptor == null) ? 0 : descriptor.hashCode());
		result = prime * result + ((originator == null) ? 0 : originator.hashCode());
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
		Keygroup other = (Keygroup) obj;
		if (app == null) {
			if (other.app != null)
				return false;
		} else if (!app.equals(other.app))
			return false;
		if (descriptor == null) {
			if (other.descriptor != null)
				return false;
		} else if (!descriptor.equals(other.descriptor))
			return false;
		if (originator == null) {
			if (other.originator != null)
				return false;
		} else if (!originator.equals(other.originator))
			return false;
		return true;
	}
	
}

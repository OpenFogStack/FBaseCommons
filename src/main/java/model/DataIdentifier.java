package model;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * A data identifier uniquely identifies a {@link DataRecord} by combining the {@link Keygroup} with a unique
 * id.
 * 
 * @author jonathanhasenburg
 *
 */
public class DataIdentifier {

	private static Logger logger = Logger.getLogger(DataIdentifier.class.getName());

	private Keygroup keygroup = null;
	private String dataId = null;
	
	public DataIdentifier() {
	}
	
	public DataIdentifier(String app, String originator, String descriptor, String dataId) {
		this.keygroup = new Keygroup(app, originator, descriptor);
		this.dataId = dataId;
	}
	
	public DataIdentifier(Keygroup keygroup, String dataId) {
		this.keygroup = keygroup;
		this.dataId = dataId;
	}

	public static DataIdentifier createFromJSON(String json) 
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, DataIdentifier.class);
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
	
	@Override
	public String toString() {
		return keygroup.toString() + "." + dataId;
	}
	
	public static DataIdentifier createFromString(String string) {
		if (string == null) {
			return null;
		}
		String[] split = string.split("\\.");
		if (split.length == 4) {
			return new DataIdentifier(split[0], split[1], split[2], split[3]);
		}
		return null;
	}
	
	// ************************************************************
	// Generated Code
	// ************************************************************
	
	public Keygroup getKeygroup() {
		return keygroup;
	}

	public void setKeygroup(Keygroup keygroup) {
		this.keygroup = keygroup;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataId == null) ? 0 : dataId.hashCode());
		result = prime * result + ((keygroup == null) ? 0 : keygroup.hashCode());
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
		DataIdentifier other = (DataIdentifier) obj;
		if (dataId == null) {
			if (other.dataId != null)
				return false;
		} else if (!dataId.equals(other.dataId))
			return false;
		if (keygroup == null) {
			if (other.keygroup != null)
				return false;
		} else if (!keygroup.equals(other.keygroup))
			return false;
		return true;
	}
	
}

package model.message;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import model.data.DataIdentifier;

/**
 * TODO Rename, e.g. to "Message", class should be the base class for all types of requests -> NodeConfig.messagePort
 * Message Request class.
 * 
 * @author jonathanhasenburg
 *
 */
public class Request {
	
	private static Logger logger = Logger.getLogger(Request.class.getName());

	private DataIdentifier dataIdentifier = null;
	private String response = null;
	private String content = null;

	public Request() {
		
	}
	
	public Request(DataIdentifier dataIdentifier) {
		this.dataIdentifier = dataIdentifier;
	}

	public static Request createFromJSON(String json) 
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, Request.class);
		} catch (Exception e) {
			logger.error("Could not translate json to GetRequest.");
			e.printStackTrace();
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
	
	// ************************************************************
	// Generated Code
	// ************************************************************

	public DataIdentifier getDataIdentifier() {
		return dataIdentifier;
	}

	public void setDataIdentifier(DataIdentifier dataIdentifier) {
		this.dataIdentifier = dataIdentifier;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}

package model.message;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonRawValue;

import model.Entity;

/**
 * Class that can be used by components to exchange messages.
 * 
 * @author jonathanhasenburg
 *
 */
public class Message extends Entity {

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(Message.class.getName());

	/**
	 * A textual response explaining the message content
	 */
	private String textualResponse = null;
	
	/**
	 * The content of the message, usually an object in JSON format
	 */
	@JsonRawValue
	private String content = null;

	public Message() {
		
	}
	
	// ************************************************************
	// Generated Code
	// ************************************************************

	public String getTextualResponse() {
		return textualResponse;
	}

	public void setTextualResponse(String textualResponse) {
		this.textualResponse = textualResponse;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((textualResponse == null) ? 0 : textualResponse.hashCode());
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
		Message other = (Message) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (textualResponse == null) {
			if (other.textualResponse != null)
				return false;
		} else if (!textualResponse.equals(other.textualResponse))
			return false;
		return true;
	}
	
}

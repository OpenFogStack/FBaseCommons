package model.messages.datarecords;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import model.Entity;
import model.messages.SpecialStringDeserializer;

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
	private String textualInfo = null;
	
	/**
	 * The content of the message, usually an object in JSON format
	 */
	@JsonRawValue
	@JsonDeserialize(using = SpecialStringDeserializer.class)
	private String content = null;

	public Message() {
		
	}
	
	// ************************************************************
	// Generated Code
	// ************************************************************

	public String getTextualResponse() {
		return textualInfo;
	}

	public void setTextualResponse(String textualResponse) {
		this.textualInfo = textualResponse;
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
		result = prime * result + ((textualInfo == null) ? 0 : textualInfo.hashCode());
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
		if (textualInfo == null) {
			if (other.textualInfo != null)
				return false;
		} else if (!textualInfo.equals(other.textualInfo))
			return false;
		return true;
	}
	
}

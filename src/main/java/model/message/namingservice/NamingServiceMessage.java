package model.message.namingservice;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import model.message.SpecialStringDeserializer;

public class NamingServiceMessage {
	private NamingServiceCommand command;
	
	@JsonRawValue
	@JsonDeserialize(using = SpecialStringDeserializer.class)
	private String content;

	public NamingServiceMessage(NamingServiceCommand command, String content) {
		this.command = command;
		this.content = content;
	}

	public NamingServiceCommand getCommand() {
		return command;
	}

	public void setCommand(NamingServiceCommand command) {
		this.command = command;
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
		result = prime * result + ((command == null) ? 0 : command.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
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
		NamingServiceMessage other = (NamingServiceMessage) obj;
		if (command != other.command)
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		return true;
	}
}

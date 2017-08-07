package model.messages;

import org.apache.log4j.Logger;

import crypto.CryptoProvider;
import crypto.CryptoProvider.EncryptionAlgorithm;
import model.JSONable;

/**
 * Class that can be used by components to exchange messages. Offers encryption and decryption
 * capabilities for its fields.
 * 
 * @author jonathanhasenburg
 *
 */
public class Message implements JSONable {

	private static Logger logger = Logger.getLogger(Message.class.getName());

	/**
	 * A textual response explaining the message content
	 */
	private String textualInfo = null;

	/**
	 * The content of the message, usually an object in JSON format
	 */
	// Not needed because we currently encrypt the field so it is not a raw value anymore
//	@JsonRawValue
//	@JsonDeserialize(using = SpecialStringDeserializer.class)
	private String content = null;

	private Command command;

	private boolean isEncrypted = false;

	public Message() {
		
	}
	
	public boolean encryptFields(String secret, EncryptionAlgorithm algorithm) {
		
		String encrypted = null;
		
		if (this.textualInfo != null) {
			encrypted = CryptoProvider.encrypt(this.textualInfo, secret, algorithm);
			if (encrypted == null) {
				logger.error("Could not encrypt textual info: " + textualInfo);
				return false;
			}
			this.textualInfo = encrypted;
		}
		
		if (this.content != null) {
			encrypted = CryptoProvider.encrypt(this.content, secret, algorithm);
			if (encrypted == null) {
				logger.error("Could not encrypt content info: " + content);
				return false;
			}
			this.content = encrypted;
		}
		
		this.isEncrypted = true;
		return true;
	}
	
	public boolean decryptFields(String secret, EncryptionAlgorithm algorithm) {
		if (!isEncrypted) {
			return true;
		}
		
		String decrypted = null;
		
		if (this.textualInfo != null) {
			decrypted = CryptoProvider.decrypt(this.textualInfo, secret, algorithm);
			if (decrypted == null) {
				logger.error("Could not decrypt textual info: " + textualInfo);
				return false;
			}
			this.textualInfo = decrypted;
		}
		
		if (this.content != null) {
			decrypted = CryptoProvider.decrypt(this.content, secret, algorithm);
			if (decrypted == null) {
				logger.error("Could not decrypt content info: " + content);
				return false;
			}
			this.content = decrypted;
		}
		
		this.isEncrypted = false;
		return true;
	}

	// ************************************************************
	// Generated Code
	// ************************************************************

	public String getTextualInfo() {
		return textualInfo;
	}

	public void setTextualInfo(String textualInfo) {
		this.textualInfo = textualInfo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public boolean isEncrypted() {
		return isEncrypted;
	}

	public void setEncrypted(boolean isEncrypted) {
		this.isEncrypted = isEncrypted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((command == null) ? 0 : command.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + (isEncrypted ? 1231 : 1237);
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
		if (command != other.command)
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (isEncrypted != other.isEncrypted)
			return false;
		if (textualInfo == null) {
			if (other.textualInfo != null)
				return false;
		} else if (!textualInfo.equals(other.textualInfo))
			return false;
		return true;
	}

}

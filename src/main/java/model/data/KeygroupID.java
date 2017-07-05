package model.data;

import org.apache.log4j.Logger;

import model.Entity;

/**
 * Helper class to manage keygroup IDs, which act as a logical grouping unit for {@link DataRecord}.
 * 
 * @author jonathanhasenburg
 *
 */
public class KeygroupID extends Entity {

	private static Logger logger = Logger.getLogger(KeygroupID.class.getName());
	public static final String internalSeperator = "/";
	
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
	
	public KeygroupID() {
		
	}
	
	public KeygroupID(String app, String originator, String descriptor) {
		this.app = app;
		this.originator = originator;
		this.descriptor = descriptor;
	}
	
	public static KeygroupID createFromString(String string) {
		if (string == null) {
			return null;
		}
		String[] split = string.split(internalSeperator);
		if (split.length == 3) {
			return new KeygroupID(split[0], split[1], split[2]);
		}
		logger.error(string + " is not a valid keygroup");
		return null;
	}
	
	@Override
	public String toString() {
		return app + internalSeperator + originator + internalSeperator + descriptor;
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
		KeygroupID other = (KeygroupID) obj;
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

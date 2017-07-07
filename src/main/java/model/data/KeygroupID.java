package model.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
		if(checkID(app, originator, descriptor)) {
			this.app = app;
			this.originator = originator;
			this.descriptor = descriptor;
		} else {
			throw new IllegalArgumentException("Invalid app, originator, and/or descriptor name.");
		}
	}
	
	public boolean checkID(String... input) {
		Pattern pattern = Pattern.compile("([A-Za-z0-9][A-Za-z0-9|_|-|(|)|&|.]*");
		
		for(String s : input) {
			Matcher matcher = pattern.matcher(s);
			
			// Ensure path uses only accepted characters
			if(!matcher.matches()) {
				return false;
			}
		}
		
		return true;
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
	
	@JsonIgnore
	public String getAppPath() {
		return app;
	}
	
	@JsonIgnore
	public String getOriginatorPath() {
		return app + internalSeperator + originator;
	}
	
	@JsonIgnore
	public String getDescriptorPath() {
		return app + internalSeperator + originator + internalSeperator + descriptor;
	}
	
	@Override
	public String toString() {
		return getDescriptorPath();
	}
		
	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		if(checkID(app)) {
			this.app = app;
		} else {
			throw new IllegalArgumentException("Invalid app name " + app);
		}
	}

	public String getOriginator() {
		return originator;
	}

	public void setOriginator(String originator) {
		if(checkID(originator)) {
			this.originator = originator;
		} else {
			throw new IllegalArgumentException("Invalid originator name " + originator);
		}
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		if(checkID(descriptor)) {
			this.descriptor = descriptor;
		} else {
			throw new IllegalArgumentException("Invalid descriptor name " + descriptor);
		}
	}
	
	// ************************************************************
	// Generated Code
	// ************************************************************

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

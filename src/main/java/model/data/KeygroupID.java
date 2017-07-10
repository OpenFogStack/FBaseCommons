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
	public static final String INTERNAL_SEPARATOR = "/";
	
	/**
	 * An identifier for the belonging application.
	 */
	private String app;
	
	/**
	 * An identifier for the origin of the data record. Typically the tenant.
	 */
	private String tenant;
	
	/**
	 * An identifier that describes the data record, e.g. brightness.
	 */
	private String group;
	
	public KeygroupID() {
		
	}
	
public KeygroupID(String app, String tenant, String group) {
		if(checkID(app, tenant, group)) {
			this.app = app;
			this.tenant = tenant;
			this.group = group;
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
		String[] split = string.split(INTERNAL_SEPARATOR);
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
	public String getTenantPath() {
		return app + INTERNAL_SEPARATOR + tenant;
	}
	
	@JsonIgnore
	public String getGroupPath() {
		return app + INTERNAL_SEPARATOR + tenant + INTERNAL_SEPARATOR + group;
	}
	
	@Override
	public String toString() {
		return getGroupPath();
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

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		if(checkID(tenant)) {
			this.tenant = tenant;
		} else {
			throw new IllegalArgumentException("Invalid tenant name " + tenant);
		}
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		if(checkID(group)) {
			this.group = group;
		} else {
			throw new IllegalArgumentException("Invalid group name " + group);
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
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((tenant == null) ? 0 : tenant.hashCode());
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
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (tenant == null) {
			if (other.tenant != null)
				return false;
		} else if (!tenant.equals(other.tenant))
			return false;
		return true;
	}

}

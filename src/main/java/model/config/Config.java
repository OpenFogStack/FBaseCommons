package model.config;

import com.fasterxml.jackson.annotation.JsonIgnore;

import model.JSONable;
import model.data.ConfigID;

/**
 * Base class for all config classes used in FBase.
 * 
 * @author jonathanhasenburg
 *
 */
public abstract class Config implements JSONable {

	private int version;

	/**
	 * Method to return the ID of any config object
	 * 
	 * @return the ID of the config object
	 */
	@JsonIgnore
	abstract ConfigID getID();

	/**
	 * Returns the version of the keygroup config. Should only be set by the naming service,
	 * as it is the single source of truth in FBase.
	 * 
	 * @return see above
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * Sets the version of the keygroup config. Should only be used by the naming service,
	 * as it is the single source of truth in FBase.
	 * 
	 * @param version - the new version of the config
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + version;
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
		Config other = (Config) obj;
		if (version != other.version)
			return false;
		return true;
	}

}

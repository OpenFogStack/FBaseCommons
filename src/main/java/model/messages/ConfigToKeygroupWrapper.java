package model.messages;

import model.JSONable;
import model.config.Config;
import model.data.KeygroupID;

public class ConfigToKeygroupWrapper implements JSONable {
	KeygroupID keygroupID = null;
	Config config = null;

	public ConfigToKeygroupWrapper() {
		
	}
	
	public ConfigToKeygroupWrapper(KeygroupID keygroupID, Config config) {
		this.keygroupID = keygroupID;
		this.config = config;
	}

	public KeygroupID getKeygroupID() {
		return keygroupID;
	}

	public void setKeygroupID(KeygroupID keygroupID) {
		this.keygroupID = keygroupID;
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((config == null) ? 0 : config.hashCode());
		result = prime * result + ((keygroupID == null) ? 0 : keygroupID.hashCode());
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
		ConfigToKeygroupWrapper other = (ConfigToKeygroupWrapper) obj;
		if (config == null) {
			if (other.config != null)
				return false;
		} else if (!config.equals(other.config))
			return false;
		if (keygroupID == null) {
			if (other.keygroupID != null)
				return false;
		} else if (!keygroupID.equals(other.keygroupID))
			return false;
		return true;
	}
}

package model.messages;

import model.JSONable;
import model.config.Config;
import model.data.ConfigID;
import model.data.KeygroupID;

public class ConfigIDToKeygroupWrapper<T extends ConfigID> implements JSONable {
	KeygroupID keygroupID = null;
	T configID = null;

	public ConfigIDToKeygroupWrapper() {
		
	}
	
	public ConfigIDToKeygroupWrapper(KeygroupID keygroupID, T configID) {
		this.keygroupID = keygroupID;
		this.configID = configID;
	}

	public KeygroupID getKeygroupID() {
		return keygroupID;
	}

	public void setKeygroupID(KeygroupID keygroupID) {
		this.keygroupID = keygroupID;
	}

	public T getConfigID() {
		return configID;
	}

	public void setConfigID(T configID) {
		this.configID = configID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((configID == null) ? 0 : configID.hashCode());
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
		ConfigIDToKeygroupWrapper other = (ConfigIDToKeygroupWrapper) obj;
		if (configID == null) {
			if (other.configID != null)
				return false;
		} else if (!configID.equals(other.configID))
			return false;
		if (keygroupID == null) {
			if (other.keygroupID != null)
				return false;
		} else if (!keygroupID.equals(other.keygroupID))
			return false;
		return true;
	}

	
}

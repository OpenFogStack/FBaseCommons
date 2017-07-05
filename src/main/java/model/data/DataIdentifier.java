package model.data;

import org.apache.log4j.Logger;

/**
 * A data identifier uniquely identifies a {@link DataRecord} by combining the {@link KeygroupID} with a unique
 * id.
 * 
 * @author jonathanhasenburg
 *
 */
public class DataIdentifier {

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(DataIdentifier.class.getName());

	private KeygroupID keygroupID = null;
	private String dataId = null;
	
	public DataIdentifier() {
		
	}
	
	public DataIdentifier(String app, String originator, String descriptor, String dataId) {
		this.keygroupID = new KeygroupID(app, originator, descriptor);
		this.dataId = dataId;
	}
	
	public DataIdentifier(KeygroupID keygroupID, String dataId) {
		this.keygroupID = keygroupID;
		this.dataId = dataId;
	}
	
	@Override
	public String toString() {
		return keygroupID.toString() + KeygroupID.internalSeperator + dataId;
	}
	
	public static DataIdentifier createFromString(String string) {
		if (string == null) {
			return null;
		}
		String[] split = string.split(KeygroupID.internalSeperator);
		if (split.length == 4) {
			return new DataIdentifier(split[0], split[1], split[2], split[3]);
		}
		return null;
	}
	
	// ************************************************************
	// Generated Code
	// ************************************************************
	
	public KeygroupID getKeygroup() {
		return keygroupID;
	}

	public void setKeygroup(KeygroupID keygroupID) {
		this.keygroupID = keygroupID;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataId == null) ? 0 : dataId.hashCode());
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
		DataIdentifier other = (DataIdentifier) obj;
		if (dataId == null) {
			if (other.dataId != null)
				return false;
		} else if (!dataId.equals(other.dataId))
			return false;
		if (keygroupID == null) {
			if (other.keygroupID != null)
				return false;
		} else if (!keygroupID.equals(other.keygroupID))
			return false;
		return true;
	}
	
}

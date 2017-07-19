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
	private String dataID = null;
	
	/**
	 * Empty constructor used for JSON parsing
	 */
	public DataIdentifier() {
		
	}
	
	/**
	 * 
	 * @param app appID (first part of keygroup identifier)
	 * @param tenant tenantID (second part of keygroup identifier)
	 * @param group groupID (third part of keygroup identifier)
	 * @param dataID name of the data item
	 */
	public DataIdentifier(String app, String tenant, String group, String dataID) {
		this(new KeygroupID(app, tenant, group), dataID);
	}
	
	/**
	 * 
	 * @param keygroupID keygroup identifier
	 * @param dataID name of the data item
	 */
	public DataIdentifier(KeygroupID keygroupID, String dataID) {
		this.keygroupID = keygroupID;
		this.dataID = dataID;
	}
	
	@Override
	public String toString() {
		return keygroupID.toString() + KeygroupID.INTERNAL_SEPARATOR + dataID;
	}
	
	public static DataIdentifier createFromString(String string) {
		if (string == null) {
			return null;
		}
		String[] split = string.split(KeygroupID.INTERNAL_SEPARATOR);
		if (split.length == 4) {
			return new DataIdentifier(split[0], split[1], split[2], split[3]);
		}
		return null;
	}
		
	// ************************************************************
	// Generated Code
	// ************************************************************
	
	public KeygroupID getKeygroupID() {
		return keygroupID;
	}

	public void setKeygroupID(KeygroupID keygroupID) {
		this.keygroupID = keygroupID;
	}

	public String getDataID() {
		return dataID;
	}

	public void setDataID(String dataID) {
		this.dataID = dataID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataID == null) ? 0 : dataID.hashCode());
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
		if (dataID == null) {
			if (other.dataID != null)
				return false;
		} else if (!dataID.equals(other.dataID))
			return false;
		if (keygroupID == null) {
			if (other.keygroupID != null)
				return false;
		} else if (!keygroupID.equals(other.keygroupID))
			return false;
		return true;
	}
	
}

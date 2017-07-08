package model.data;

import java.util.HashMap;
import java.util.Map;

import model.Entity;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A DataRecord represents a single data item that is stored by FBase.
 * 
 * 
 * @author jonathanhasenburg
 *
 */
public class DataRecord extends Entity {

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(DataRecord.class.getName());

	private DataIdentifier dataIdentifier = null;
	private Long createTime = null;
	private Long updateTime = null;
	private Map<String,String> value;

	public DataRecord() {
		
	}

	/**
	 * 
	 * @param dataIdentifier keygroup of the data record plus data Id(key)
	 * @param value values of the data record
	 */
	public DataRecord(DataIdentifier dataIdentifier, Map<String,String> value) {
		this.dataIdentifier = dataIdentifier;
		this.value = value;
	}

	@JsonIgnore
	public KeygroupID getKeygroupID() {
		if (dataIdentifier == null) {
			return null;
		}
		return dataIdentifier.getKeygroupID();
	}

	@JsonIgnore
	public String getDataId() {
		if (dataIdentifier == null) {
			return null;
		}
		return dataIdentifier.getDataId();
	}
	
	@JsonIgnore
	public void setValueWithoutKey(String value) {
		if (this.value == null) {
			this.value = new HashMap<>();
		}
		this.value.put("", value);
	}
	
	@Override
	public String toString() {
		return super.toJSON();
	}

	// ************************************************************
	// Generated Code
	// ************************************************************

	public DataIdentifier getDataIdentifier() {
		return dataIdentifier;
	}

	public void setDataIdentifier(DataIdentifier dataIdentifier) {
		this.dataIdentifier = dataIdentifier;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Map<String,String> getValue() {
		return value;
	}

	public void setValue(Map<String,String> value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((dataIdentifier == null) ? 0 : dataIdentifier.hashCode());
		result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		DataRecord other = (DataRecord) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (dataIdentifier == null) {
			if (other.dataIdentifier != null)
				return false;
		} else if (!dataIdentifier.equals(other.dataIdentifier))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}

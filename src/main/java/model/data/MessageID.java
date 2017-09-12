package model.data;

import org.javatuples.Triplet;

import com.fasterxml.jackson.annotation.JsonIgnore;

import exceptions.FBaseException;

public class MessageID {

	private Triplet<NodeID, String, Integer> triplet =
			new Triplet<NodeID, String, Integer>(null, null, null);

	public MessageID() {

	}

	public MessageID(NodeID nodeID, String machineName, Integer version) {
		triplet = new Triplet<NodeID, String, Integer>(nodeID, machineName, version);
	}

	@JsonIgnore
	public NodeID getNodeID() {
		return triplet.getValue0();
	}
	
	@JsonIgnore
	public String getMachineName() {
		return triplet.getValue1();
	}
	
	@JsonIgnore
	public int getVersion() {
		return triplet.getValue2();
	}

	public String getMessageIDString() {
		return triplet.getValue0().getID() + "/" + triplet.getValue1() + "/" + triplet.getValue2();
	}

	public void setMessageIDString(String string) throws FBaseException {
		if (string == null) {
			throw new FBaseException("Input string must not be null");
		}
		String[] split = string.split("/");
		if (split.length == 3) {
			try {
				triplet = new Triplet<NodeID, String, Integer>(new NodeID(split[0]), split[1],
						Integer.parseInt(split[2]));
			} catch (NumberFormatException e) {
				throw new FBaseException("Could not create messageID with " + string);
			}
		} else {
			throw new FBaseException(
					"The given message ID must have three fields seperated by / .");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((triplet == null) ? 0 : triplet.hashCode());
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
		MessageID other = (MessageID) obj;
		if (triplet == null) {
			if (other.triplet != null)
				return false;
		} else if (!triplet.equals(other.triplet))
			return false;
		return true;
	}

}

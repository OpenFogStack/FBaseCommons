package model;

import crypto.CryptoProvider.EncryptionAlgorithm;

public class KeygroupConfiguration {

	private Keygroup keygroup = null;
	private String encryptionSecret = "";
	private EncryptionAlgorithm encryptionAlgorithm = null;
	
	public KeygroupConfiguration(Keygroup keygroup, String encryptionSecret, 
			EncryptionAlgorithm encryptionAlgorithm) {
		this.keygroup = keygroup;
		this.encryptionSecret = encryptionSecret;
		this.encryptionAlgorithm = encryptionAlgorithm;
	}
	
	public Keygroup getKeygroup() {
		return keygroup;
	}

	public String getEncryptionSecret() {
		return encryptionSecret;
	}

	public EncryptionAlgorithm getEncryptionAlgorithm() {
		return encryptionAlgorithm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((encryptionAlgorithm == null) ? 0 : encryptionAlgorithm.hashCode());
		result = prime * result + ((encryptionSecret == null) ? 0 : encryptionSecret.hashCode());
		result = prime * result + ((keygroup == null) ? 0 : keygroup.hashCode());
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
		KeygroupConfiguration other = (KeygroupConfiguration) obj;
		if (encryptionAlgorithm != other.encryptionAlgorithm)
			return false;
		if (encryptionSecret == null) {
			if (other.encryptionSecret != null)
				return false;
		} else if (!encryptionSecret.equals(other.encryptionSecret))
			return false;
		if (keygroup == null) {
			if (other.keygroup != null)
				return false;
		} else if (!keygroup.equals(other.keygroup))
			return false;
		return true;
	}
	
}

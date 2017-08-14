package model.messages;

import org.apache.log4j.Logger;

import crypto.AlgorithmAES;
import crypto.CryptoProvider;
import crypto.CryptoProvider.EncryptionAlgorithm;
import crypto.IAlgorithm.AlgorithmType;
import exceptions.FBaseEncryptionException;
import model.JSONable;

/**
 * Class that can be used by components to exchange messages. Offers encryption and decryption
 * capabilities for its fields.
 * 
 * TODO 1: Asymmetric encryption with multiple iteration fails, if next key length is >=
 * current key length.
 * 
 * @author jonathanhasenburg
 *
 */
public class Message implements JSONable {

	private static Logger logger = Logger.getLogger(Message.class.getName());

	/**
	 * A textual response explaining the message content
	 */
	private String textualInfo = null;

	/**
	 * The content of the message, usually an object in JSON format
	 */
	// Not needed because we currently encrypt the field so it is not a raw value anymore
	// @JsonRawValue
	// @JsonDeserialize(using = SpecialStringDeserializer.class)
	private String content = null;

	private Command command;

	private boolean isEncrypted = false;

	/**
	 * If {@link #encryptFields(String, EncryptionAlgorithm)} is used with an asymmetric
	 * algorithm, the {@link #content} and {@link #textualInfo} fields are not encrypted with
	 * the chosen algorithm, but an newly generated AES secret. This secret is stored in this
	 * field and encrypted with the asymmetric algorithm, instead. If the field is not null,
	 * all decryptions are performed on this field, as well.
	 * 
	 * When successful, {@link #isEncrypted()} will return true.
	 */
	private String symmetricSecret = null;

	public Message() {

	}

	public Message(Command command, String content) {
		this.command = command;
		this.content = content;
	}

	public Message(Command command, String content, String textualInfo) {
		this.textualInfo = textualInfo;
		this.command = command;
		this.content = content;
	}

	public Message createCopy() {
		Message m = new Message();
		m.setCommand(this.command);
		m.setTextualInfo(this.textualInfo);
		m.setEncrypted(this.isEncrypted);
		m.setSymmetricSecret(this.symmetricSecret);
		m.setContent(this.content);
		return m;
	}

	/**
	 * Same as {@link #encryptFields(String[], EncryptionAlgorithm[])}, but only one
	 * secret/algorithm.
	 * 
	 * @param secret
	 * @param algorithm
	 * @throws FBaseEncryptionException
	 */
	public void encryptFields(String secret, EncryptionAlgorithm algorithm)
			throws FBaseEncryptionException {
		EncryptionAlgorithm[] algorithms = new EncryptionAlgorithm[1];
		algorithms[0] = algorithm;
		String[] secrets = { secret };
		encryptFields(secrets, algorithms);
	}

	/**
	 * Encrypts the message's fields using the given secrets and algorithms. Can also encrypt
	 * large fields using asymmetric encryption by generating a random AES secret which is
	 * used to encrypt the actual data, while the generated secret is encrypted with the
	 * provided asymmetric key.
	 * 
	 * If more than one secret/algorithm is provided, all of them should be asymmetric or
	 * symmetric, but not a combination.
	 * 
	 * @param secrets
	 * @param algorithms
	 * @throws FBaseEncryptionException
	 */
	public void encryptFields(String[] secrets, EncryptionAlgorithm[] algorithms)
			throws FBaseEncryptionException {

		if (secrets.length != algorithms.length) {
			throw new IllegalArgumentException("The given number of "
					+ "secrets should equal the given number of algorithms.");
		}

		if (isEncrypted) {
			throw new FBaseEncryptionException(FBaseEncryptionException.ALREADY_ENCRYPTED);
		}

		for (EncryptionAlgorithm algorithm : algorithms) {
			if (!CryptoProvider.chooseAlgorithm(algorithms[0]).getType()
					.equals(CryptoProvider.chooseAlgorithm(algorithm).getType())) {
				throw new FBaseEncryptionException(
						FBaseEncryptionException.INCOMPATIBEL_ALGORITHMS);
			}
		}

		if (AlgorithmType.ASYMMETRIC
				.equals(CryptoProvider.chooseAlgorithm(algorithms[0]).getType())) {
			// an asymmetric algorithm will lead to the approach described in javadoc
			symmetricSecret = AlgorithmAES.generateNewSecret();
			encryptFieldsSymmetric(symmetricSecret, EncryptionAlgorithm.AES);

			for (int i = 0; i < secrets.length; i++) {
				String encrypted = null;
				encrypted = CryptoProvider.encrypt(this.symmetricSecret, secrets[i], algorithms[i]);
				if (encrypted == null) {
					String errorMessage =
							"Could not encrypt the symmetricSecret: " + symmetricSecret;
					logger.error(errorMessage);
					throw new FBaseEncryptionException(errorMessage);
				}
				this.symmetricSecret = encrypted;
			}

		} else {
			// encrypt symmetrically
			for (int i = 0; i < secrets.length; i++) {
				encryptFieldsSymmetric(secrets[i], algorithms[i]);
			}
		}

		this.isEncrypted = true;
	}

	private void encryptFieldsSymmetric(String secret, EncryptionAlgorithm algorithm)
			throws FBaseEncryptionException {
		String encrypted = null;

		if (this.textualInfo != null) {
			encrypted = CryptoProvider.encrypt(this.textualInfo, secret, algorithm);
			if (encrypted == null) {
				String errorMessage = "Could not encrypt textual info: " + textualInfo;
				logger.error(errorMessage);
				throw new FBaseEncryptionException(errorMessage);
			}
			this.textualInfo = encrypted;
		}

		if (this.content != null) {
			encrypted = CryptoProvider.encrypt(this.content, secret, algorithm);
			if (encrypted == null) {
				String errorMessage = "Could not encrypt content info: " + content;
				logger.error(errorMessage);
				throw new FBaseEncryptionException(errorMessage);
			}
			this.content = encrypted;
		}

	}

	/**
	 * Same as {@link #decryptFields(String[], EncryptionAlgorithm[])}, but only one
	 * secret/algorithm.
	 * 
	 * @param secret
	 * @param algorithm
	 * @throws FBaseEncryptionException
	 */
	public void decryptFields(String secret, EncryptionAlgorithm algorithm)
			throws FBaseEncryptionException {
		EncryptionAlgorithm[] algorithms = new EncryptionAlgorithm[1];
		algorithms[0] = algorithm;
		String[] secrets = { secret };
		decryptFields(secrets, algorithms);
	}

	/**
	 * Decrypts the message's fields using the given secrets and algorithms. Fails if not
	 * compatibel to {@link #encryptFields(String[], EncryptionAlgorithm[])}.
	 * 
	 * If more than one secret/algorithm is provided, all of them should be asymmetric or
	 * symmetric, but not a combination.
	 * 
	 * @param secrets
	 * @param algorithms
	 * @throws FBaseEncryptionException
	 */
	public void decryptFields(String[] secrets, EncryptionAlgorithm[] algorithms)
			throws FBaseEncryptionException {

		if (secrets.length != algorithms.length) {
			throw new IllegalArgumentException("The given number of "
					+ "secrets should equal the given number of algorithms.");
		}

		if (!isEncrypted) {
			throw new FBaseEncryptionException(FBaseEncryptionException.NOT_ENCRYPTED);
		}

		for (EncryptionAlgorithm algorithm : algorithms) {
			if (!CryptoProvider.chooseAlgorithm(algorithms[0]).getType()
					.equals(CryptoProvider.chooseAlgorithm(algorithm).getType())) {
				throw new FBaseEncryptionException(
						FBaseEncryptionException.INCOMPATIBEL_ALGORITHMS);
			}
		}

		if (AlgorithmType.ASYMMETRIC
				.equals(CryptoProvider.chooseAlgorithm(algorithms[0]).getType())) {
			// there needs to be a symmetricSecret if asymmetric
			if (symmetricSecret == null) {
				throw new FBaseEncryptionException(FBaseEncryptionException.SYMMETRIC_KEY_MISSING);
			}

			for (int i = 0; i < secrets.length; i++) {
				String decrypted = null;
				decrypted = CryptoProvider.decrypt(this.symmetricSecret, secrets[i], algorithms[i]);
				if (decrypted == null) {
					String errorMessage =
							"Could not decrypt the symmetricSecret: " + symmetricSecret;
					logger.error(errorMessage);
					throw new FBaseEncryptionException(errorMessage);
				}
				this.symmetricSecret = decrypted;
			}

			decryptFieldsSymmetric(symmetricSecret, EncryptionAlgorithm.AES);
			symmetricSecret = null; // clean up secret
		} else {
			// decrypt symmetrically
			for (int i = 0; i < secrets.length; i++) {
				decryptFieldsSymmetric(secrets[i], algorithms[i]);
			}
		}

		this.isEncrypted = false;
	}

	public void decryptFieldsSymmetric(String secret, EncryptionAlgorithm algorithm)
			throws FBaseEncryptionException {

		String decrypted = null;

		if (this.textualInfo != null) {
			decrypted = CryptoProvider.decrypt(this.textualInfo, secret, algorithm);
			if (decrypted == null) {
				String errorMessage = "Could not decrypt textual info: " + textualInfo;
				logger.error(errorMessage);
				throw new FBaseEncryptionException(errorMessage);
			}
			this.textualInfo = decrypted;
		}

		if (this.content != null) {
			decrypted = CryptoProvider.decrypt(this.content, secret, algorithm);
			if (decrypted == null) {
				String errorMessage = "Could not decrypt content info: " + content;
				logger.error(errorMessage);
				throw new FBaseEncryptionException(errorMessage);
			}
			this.content = decrypted;
		}

	}

	// ************************************************************
	// Generated Code
	// ************************************************************

	public String getTextualInfo() {
		return textualInfo;
	}

	public void setTextualInfo(String textualInfo) {
		this.textualInfo = textualInfo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public boolean isEncrypted() {
		return isEncrypted;
	}

	public void setEncrypted(boolean isEncrypted) {
		this.isEncrypted = isEncrypted;
	}

	public String getSymmetricSecret() {
		return symmetricSecret;
	}

	public void setSymmetricSecret(String symmetricSecret) {
		this.symmetricSecret = symmetricSecret;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((command == null) ? 0 : command.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + (isEncrypted ? 1231 : 1237);
		result = prime * result + ((symmetricSecret == null) ? 0 : symmetricSecret.hashCode());
		result = prime * result + ((textualInfo == null) ? 0 : textualInfo.hashCode());
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
		Message other = (Message) obj;
		if (command != other.command)
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (isEncrypted != other.isEncrypted)
			return false;
		if (symmetricSecret == null) {
			if (other.symmetricSecret != null)
				return false;
		} else if (!symmetricSecret.equals(other.symmetricSecret))
			return false;
		if (textualInfo == null) {
			if (other.textualInfo != null)
				return false;
		} else if (!textualInfo.equals(other.textualInfo))
			return false;
		return true;
	}

}

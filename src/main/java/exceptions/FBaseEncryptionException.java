/**
 * 
 */
package exceptions;

/**
 * Exception class for causes that are related to encryption and decryption.
 * 
 * @author jonathanhasenburg
 *
 */
public class FBaseEncryptionException extends FBaseException {

	public static final String ALREADY_ENCRYPTED =
			"The fields of the chosen object are already encrypted. "
					+ "Encrypting them again is not allowed because it might lead to "
					+ "unexpected behavior.";

	public static final String NOT_ENCRYPTED =
			"The message objects fields seem to be not encrypted.";

	public static final String INCOMPATIBEL_ALGORITHMS =
			"It seems like you want to encrypt something using symmetric and asymmetric algorithms. "
					+ "This is not supported.";

	public static final String ENCRYPTION_FAILED = "Could not encrypt/decrypt!";

	public static final String SYMMETRIC_KEY_MISSING =
			"The secret used to encrypt the actual data is missing.";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public FBaseEncryptionException(String message) {
		super(message);
	}

}

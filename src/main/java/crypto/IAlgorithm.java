package crypto;

public interface IAlgorithm {

	/**
	 * Encrypts the given String.
	 * 
	 * @param toEncrypt
	 *            - the String to encrypt
	 * @param secret
	 *            - the secret used for encryption
	 * @return the cipher or null, if something goes wrong
	 */
	String encrypt(String toEncrypt, String secret);

	/**
	 * Decrypts the given String.
	 * 
	 * @param toDecrypt
	 *            - the String to decrypt
	 * @param secret
	 *            - the secret used for decryption
	 * @return a decrypted String or null, if something goes wrong
	 */
	String decrypt(String toDecrypt, String secret);

}

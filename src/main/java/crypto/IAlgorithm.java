package crypto;

import exceptions.FBaseEncryptionException;

public interface IAlgorithm {

	public enum AlgorithmType {
		ASYMMETRIC, SYMMETRIC
	}

	/**
	 * Encrypts the given String.
	 * 
	 * @param toEncrypt - the String to encrypt
	 * @param secret - the secret or public/private key used for encryption
	 * @return the cipher or null, if something goes wrong
	 */
	String encrypt(String toEncrypt, String secret);

	/**
	 * Decrypts the given String.
	 * 
	 * @param toDecrypt - the String to decrypt
	 * @param secret - the secret or public/private key used for decryption
	 * @return a decrypted String or null, if something goes wrong
	 */
	String decrypt(String toDecrypt, String secret);

	/**
	 * Returns the {@link AlgorithmType} of the chosen algorithm.
	 * 
	 * @return see above
	 */
	AlgorithmType getType();

	/**
	 * Signs the content with the given secret and creates a signature. Will throw an
	 * {@link FBaseEncryptionException} if the algorithm does not support signing.
	 * 
	 * @param content - the content to sign
	 * @param privateKey - the privateKey used to sign the content
	 * @return the resulting signature
	 */
	String sign(String content, String privateKey) throws FBaseEncryptionException;

	/**
	 * Verifies the given content based on a given signature and publicKey. Will throw an
	 * {@link FBaseEncryptionException} if the algorithm does not support verification.
	 * 
	 * @param content - the content used for verification
	 * @param publicKey - the publicKEy used to decrypt
	 * @param signature - the signature used for verification
	 * @return boolean, if verified
	 * @throws FBaseEncryptionException
	 */
	boolean verify(String content, String publicKey, String signature)
			throws FBaseEncryptionException;

}

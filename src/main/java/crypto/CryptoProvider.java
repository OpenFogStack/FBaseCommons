package crypto;

import org.apache.log4j.Logger;

import exceptions.FBaseEncryptionException;

public class CryptoProvider {

	private static Logger logger = Logger.getLogger(CryptoProvider.class.getName());

	public enum EncryptionAlgorithm {
		AES, RSA
	}

	public static String encrypt(String toEncrypt, String secret, EncryptionAlgorithm algorithm) {
		IAlgorithm alg = chooseAlgorithm(algorithm);
		if (alg == null) {
			logger.error("No valid algorithm hase been chosen for encryption.");
			return null;
		}
		return alg.encrypt(toEncrypt, secret);
	}

	public static String decrypt(String toDecrypt, String secret, EncryptionAlgorithm algorithm) {
		IAlgorithm alg = chooseAlgorithm(algorithm);
		if (alg == null) {
			logger.error("No valid algorithm hase been chosen for decryption.");
			return null;
		}
		return alg.decrypt(toDecrypt, secret);
	}

	public static String sign(String content, String privateKey, EncryptionAlgorithm algorithm)
			throws FBaseEncryptionException {
		IAlgorithm alg = chooseAlgorithm(algorithm);
		if (alg == null) {
			logger.error("No valid algorithm hase been chosen for signing.");
			return null;
		}
		return alg.sign(content, privateKey);
	}

	public static boolean verify(String content, String signature, String publicKey,
			EncryptionAlgorithm algorithm) throws FBaseEncryptionException {
		IAlgorithm alg = chooseAlgorithm(algorithm);
		if (alg == null) {
			logger.error("No valid algorithm hase been chosen for verification.");
			return false;
		}
		return alg.verify(content, publicKey, signature);
	}

	public static IAlgorithm chooseAlgorithm(EncryptionAlgorithm algorithm) {
		if (algorithm.equals(EncryptionAlgorithm.AES)) {
			return new AlgorithmAES();
		} else if (algorithm.equals(EncryptionAlgorithm.RSA)) {
			return new AlgorithmRSA();
		}
		return null;
	}
}

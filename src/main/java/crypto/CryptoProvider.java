package crypto;

import org.apache.log4j.Logger;

public class CryptoProvider {

	private static Logger logger = Logger.getLogger(CryptoProvider.class.getName());
	
	public enum EncryptionAlgorithm {
		AES
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
			logger.error("No valid algorithm hase been chosen for encryption.");
			return null;
		}
		return alg.decrypt(toDecrypt, secret);
	}
	
	
	private static IAlgorithm chooseAlgorithm(EncryptionAlgorithm algorithm) {
		if (algorithm.equals(EncryptionAlgorithm.AES)) {
			return new AlgorithmAES();
		}
		return null;
	}
}

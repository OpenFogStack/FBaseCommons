package crypto;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.apache.log4j.Logger;
import org.javatuples.Pair;
import org.junit.Test;

import crypto.CryptoProvider.EncryptionAlgorithm;

public class CryptoProviderTest {

	private static Logger logger = Logger.getLogger(CryptoProviderTest.class.getName());

	@Test
	public void testAES() {
		logger.debug("-------Starting testAES-------");
		String toEncrypt = "testAES";
		String secret = "testSecret";
		String toDecrypt = CryptoProvider.encrypt(toEncrypt, secret, EncryptionAlgorithm.AES);
		logger.debug("Decrypted text = " + toDecrypt);
		String decrypted = CryptoProvider.decrypt(toDecrypt, secret, EncryptionAlgorithm.AES);
		assertEquals(toEncrypt, decrypted);
		logger.debug("Finished testAES.");
	}

	@Test
	public void testRSA_PrivateEncrypt() throws NoSuchAlgorithmException {
		logger.debug("-------Starting testRSA_PrivateEncrypt-------");
		String toEncrypt = "testRSA_PrivateEncrypt";

		Pair<PublicKey, PrivateKey> keyPair = RSAHelper.generateKeyPair(2048);
		logger.info("Private key: " + keyPair.getValue1());
		logger.info("Public key: " + keyPair.getValue0());
		
		String encryptionSecret = RSAHelper.getEncodedStringFromKey(keyPair.getValue1());
		logger.debug("Encryption Secret: " + encryptionSecret);
		String decryptionSecret = RSAHelper.getEncodedStringFromKey(keyPair.getValue0());
		logger.debug("Decryption Secret: " + decryptionSecret);

		String toDecrypt = CryptoProvider.encrypt(toEncrypt, encryptionSecret,
				EncryptionAlgorithm.RSA_PRIVATE_ENCRYPT);
		logger.debug("Encrypted text = " + toDecrypt);
		String decrypted = CryptoProvider.decrypt(toDecrypt, decryptionSecret,
				EncryptionAlgorithm.RSA_PRIVATE_ENCRYPT);
		logger.debug("Decrypted text = " + decrypted);
		assertEquals(toEncrypt, decrypted);
		logger.debug("Finished testRSA_PrivateEncrypt.");
	}

}

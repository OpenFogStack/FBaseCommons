package crypto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.apache.log4j.Logger;
import org.javatuples.Pair;
import org.junit.Test;

import crypto.CryptoProvider.EncryptionAlgorithm;
import exceptions.FBaseEncryptionException;

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
	public void testRSA_SignAndVerify() throws NoSuchAlgorithmException, FBaseEncryptionException {
		logger.debug("-------Starting testRSA_SignAndVerify-------");
		String content = "testRSA_SignAndVerify";

		Pair<PublicKey, PrivateKey> keyPair = RSAHelper.generateKeyPair(2048);
		logger.info("Private key: " + keyPair.getValue1());
		logger.info("Public key: " + keyPair.getValue0());

		String privateKey = RSAHelper.getEncodedStringFromKey(keyPair.getValue1());
		logger.debug("Private key: " + privateKey);
		String publicKey = RSAHelper.getEncodedStringFromKey(keyPair.getValue0());
		logger.debug("Public key: " + publicKey);

		String signature = CryptoProvider.sign(content, privateKey, EncryptionAlgorithm.RSA);
		logger.debug("Signature = " + signature);
		boolean verified =
				CryptoProvider.verify(content, signature, publicKey, EncryptionAlgorithm.RSA);
		assertTrue(verified);
		logger.debug("Finished testRSA_SignAndVerify.");
	}

	@Test
	public void testRSA() throws NoSuchAlgorithmException {
		logger.debug("-------Starting testRSA-------");
		String toEncrypt = "testRSA";

		Pair<PublicKey, PrivateKey> keyPair = RSAHelper.generateKeyPair(2048);
		logger.info("Private key: " + keyPair.getValue1());
		logger.info("Public key: " + keyPair.getValue0());

		String encryptionSecret = RSAHelper.getEncodedStringFromKey(keyPair.getValue0());
		logger.debug("Encryption Secret: " + encryptionSecret);
		String decryptionSecret = RSAHelper.getEncodedStringFromKey(keyPair.getValue1());
		logger.debug("Decryption Secret: " + decryptionSecret);

		String toDecrypt =
				CryptoProvider.encrypt(toEncrypt, encryptionSecret, EncryptionAlgorithm.RSA);
		logger.debug("Encrypted text = " + toDecrypt);
		String decrypted =
				CryptoProvider.decrypt(toDecrypt, decryptionSecret, EncryptionAlgorithm.RSA);
		logger.debug("Decrypted text = " + decrypted);
		assertEquals(toEncrypt, decrypted);
		logger.debug("Finished testRSA.");
	}

}

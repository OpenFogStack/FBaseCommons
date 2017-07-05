package crypto;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
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
		String enrypted = CryptoProvider.decrypt(toDecrypt, secret, EncryptionAlgorithm.AES);
		assertEquals(toEncrypt, enrypted);
		logger.debug("Finished testAES.");
	}

}

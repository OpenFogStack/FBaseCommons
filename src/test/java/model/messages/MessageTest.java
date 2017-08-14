package model.messages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.apache.log4j.Logger;
import org.javatuples.Pair;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import crypto.CryptoProvider.EncryptionAlgorithm;
import crypto.RSAHelper;
import exceptions.FBaseEncryptionException;
import model.JSONable;
import model.config.KeygroupConfig;
import model.data.KeygroupID;

public class MessageTest {

	private static Logger logger = Logger.getLogger(MessageTest.class.getName());

	private Message m;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		m = new Message();
		m.setCommand(Command.KEYGROUP_CONFIG_CREATE);
		KeygroupConfig config =
				new KeygroupConfig(new KeygroupID("app", "tentant", "group"), null, null);
		m.setContent(JSONable.toJSON(config));
		m.setTextualInfo("Test-Textual Info");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAES() throws FBaseEncryptionException {
		logger.debug("-------Starting testAES-------");
		Message mCopy = m.createCopy();
		String secret = "TestAESSecret";
		mCopy.encryptFields(secret, EncryptionAlgorithm.AES);
		assertTrue(mCopy.isEncrypted());
		assertNotEquals(m, mCopy);
		logger.debug("The encrypted message is: " + JSONable.toJSON(mCopy));
		mCopy.decryptFields(secret, EncryptionAlgorithm.AES);
		assertEquals(m, mCopy);
		assertFalse(mCopy.isEncrypted());
		logger.debug("Finished testAES.");
	}

	@Test
	public void testRSA() throws FBaseEncryptionException, NoSuchAlgorithmException {
		logger.debug("-------Starting testRSA-------");
		Message mCopy = m.createCopy();
		Pair<PublicKey, PrivateKey> keys = RSAHelper.generateKeyPair(2048);

		mCopy.encryptFields(RSAHelper.getEncodedStringFromKey(keys.getValue1()),
				EncryptionAlgorithm.RSA_PRIVATE_ENCRYPT);
		assertTrue(mCopy.isEncrypted());
		assertNotNull(mCopy.getSymmetricSecret());
		logger.debug("The encrypted message is: " + JSONable.toJSON(mCopy));
		assertNotEquals(m, mCopy);
		mCopy.decryptFields(RSAHelper.getEncodedStringFromKey(keys.getValue0()),
				EncryptionAlgorithm.RSA_PRIVATE_ENCRYPT);
		assertEquals(m, mCopy);
		assertFalse(mCopy.isEncrypted());
		logger.debug("Finished testRSA.");
	}

	@Test
	public void testRSATwice() throws NoSuchAlgorithmException, FBaseEncryptionException {
		logger.debug("-------Starting testRSATwice-------");
		Message mCopy = m.createCopy();
		Pair<PublicKey, PrivateKey> keys1 = RSAHelper.generateKeyPair(1024);
		Pair<PublicKey, PrivateKey> keys2 = RSAHelper.generateKeyPair(2048);

		String[] encryptKeys = { RSAHelper.getEncodedStringFromKey(keys1.getValue1()),
				RSAHelper.getEncodedStringFromKey(keys2.getValue0()) };
		String[] decryptKeys = { RSAHelper.getEncodedStringFromKey(keys2.getValue1()),
				RSAHelper.getEncodedStringFromKey(keys1.getValue0()) };

		EncryptionAlgorithm[] encryptAlgorithms =
				{ EncryptionAlgorithm.RSA_PRIVATE_ENCRYPT, EncryptionAlgorithm.RSA_PUBLIC_ENCRYPT };
		EncryptionAlgorithm[] decryptAlgorithms = { encryptAlgorithms[1], encryptAlgorithms[0] };

		mCopy.encryptFields(encryptKeys, encryptAlgorithms);
		assertTrue(mCopy.isEncrypted());
		assertNotNull(mCopy.getSymmetricSecret());
		logger.debug("The encrypted message is: " + JSONable.toJSON(mCopy));
		assertNotEquals(m, mCopy);
		mCopy.decryptFields(decryptKeys, decryptAlgorithms);
		assertEquals(m, mCopy);
		assertFalse(mCopy.isEncrypted());
		logger.debug("Finished testRSATwice.");
	}

}

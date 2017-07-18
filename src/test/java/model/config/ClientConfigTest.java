package model.config;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import crypto.CryptoProvider.EncryptionAlgorithm;

public class ClientConfigTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		ClientConfig config = new ClientConfig();
		config.setClientID("client1");
		config.setEncryptionAlgorithm(EncryptionAlgorithm.AES);
		config.setPublicKey("client1PublicKey");
		
		String json = config.toJSON();
		System.out.println(json);
		assertEquals(config, ClientConfig.fromJSON(json, ClientConfig.class));
	}

}

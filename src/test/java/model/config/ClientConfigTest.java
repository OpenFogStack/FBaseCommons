package model.config;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import crypto.CryptoProvider.EncryptionAlgorithm;
import model.JSONable;
import model.data.ClientID;

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
		config.setClientID(new ClientID("client1"));
		config.setEncryptionAlgorithm(EncryptionAlgorithm.AES);
		config.setPublicKey("client1PublicKey");
		
		String json = JSONable.toJSON(config);
		System.out.println(json);
		assertEquals(config, JSONable.fromJSON(json, ClientConfig.class));
	}

}

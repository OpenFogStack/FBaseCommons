package model.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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

	@Test
	public void test2() {
		ClientConfig config1 = new ClientConfig();
		config1.setClientID(new ClientID("client1"));
		config1.setVersion(1);

		ClientConfig config2 = new ClientConfig();
		config2.setClientID(new ClientID("client1"));
		config2.setVersion(2);

		assertNotEquals(config1, config2);
	}

	@Test
	public void testReadFile() throws FileNotFoundException {
		FileInputStream is = new FileInputStream("src/test/resources/clientConfig.json");
		ClientConfig config = JSONable.fromJSON(is, ClientConfig.class);
		assertEquals(0, config.getVersion());
		assertEquals(new ClientID("client1"), config.getClientID());
		assertEquals("client1PublicKey", config.getPublicKey());
		assertEquals(EncryptionAlgorithm.AES, config.getEncryptionAlgorithm());
	}

}

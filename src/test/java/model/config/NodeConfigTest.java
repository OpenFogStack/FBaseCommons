package model.config;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import crypto.CryptoProvider.EncryptionAlgorithm;

public class NodeConfigTest {

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
		NodeConfig config = new NodeConfig();
		config.setNodeID("nodeA");
		config.setPublicKey("testKey");
		config.setEncryptionAlgorithm(EncryptionAlgorithm.AES); // should be RSA after implementation
		config.setMachines(Arrays.asList("Mx1", "Mx2"));
		config.setPublisherPort(8000);
		config.setMessagePort(8010);
		config.setRestPort(8080);
		config.setLocation("52.515249, 13.326476");
		config.setDescription("Raspberry Pi Cluster in EN 004");
		
		String json = config.toJSON();
		System.out.println(json);
		assertEquals(config, NodeConfig.fromJSON(json, NodeConfig.class));
	}

}

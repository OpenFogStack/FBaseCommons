package model.config;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import crypto.CryptoProvider.EncryptionAlgorithm;
import model.data.KeygroupID;

public class KeygroupConfigTest {

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
		// config
		KeygroupConfig config = new KeygroupConfig();
		config.setKeygroupID(new KeygroupID("smartlight", "h1", "brightness"));
		
		// client
		config.addClient("client1");
		
		// replica configs
		ReplicaNodeConfig rconfigA = new ReplicaNodeConfig();
		rconfigA.setNodeID("A");
		rconfigA.setTimeToLive(604800);
		config.addReplicaNode(rconfigA);
		
		ReplicaNodeConfig rconfigB = new ReplicaNodeConfig();
		rconfigB.setNodeID("B");
		rconfigB.setTimeToLive(604800);
		config.addReplicaNode(rconfigB);
		
		// trigger config
		TriggerNodeConfig tconfigC = new TriggerNodeConfig();
		tconfigC.setNodeID("C");
		config.addTriggerNode(tconfigC);
		
		// encryption
		config.setEncryptionAlgorithm(EncryptionAlgorithm.AES);
		config.setEncryptionSecret("wolkenhasen");
		
		String json = config.toJSON();
		System.out.println(json);
		assertEquals(config, KeygroupConfig.fromJSON(json, KeygroupConfig.class));
	}

}

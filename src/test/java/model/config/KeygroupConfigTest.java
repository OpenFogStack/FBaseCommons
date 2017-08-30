package model.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import crypto.CryptoProvider.EncryptionAlgorithm;
import model.JSONable;
import model.data.ClientID;
import model.data.KeygroupID;
import model.data.NodeID;

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
		config.addClient(new ClientID("client1"));
		
		// replica configs
		ReplicaNodeConfig rconfigA = new ReplicaNodeConfig();
		rconfigA.setNodeID(new NodeID("A"));
		rconfigA.setTimeToLive(604800);
		config.addReplicaNode(rconfigA);
		
		ReplicaNodeConfig rconfigB = new ReplicaNodeConfig();
		rconfigB.setNodeID(new NodeID("B"));
		rconfigB.setTimeToLive(604800);
		config.addReplicaNode(rconfigB);
		
		// trigger config
		TriggerNodeConfig tconfigC = new TriggerNodeConfig();
		tconfigC.setNodeID(new NodeID("C"));
		config.addTriggerNode(tconfigC);	
		
		// encryption
		config.setEncryptionAlgorithm(EncryptionAlgorithm.AES);
		config.setEncryptionSecret("wolkenhasen");
		
		String json = JSONable.toJSON(config);
		System.out.println(json);
		assertEquals(config, JSONable.fromJSON(json, KeygroupConfig.class));
	}
	
	@Test
	public void test2() {
		KeygroupConfig config1 = new KeygroupConfig();
		config1.setKeygroupID(new KeygroupID("smartlight", "h1", "brightness"));
		config1.setVersion(1);
		
		KeygroupConfig config2 = new KeygroupConfig();
		config2.setKeygroupID(new KeygroupID("smartlight", "h1", "brightness"));
		config2.setVersion(2);
		
		assertNotEquals(config1, config2);
	}

}

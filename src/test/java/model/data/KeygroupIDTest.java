package model.data;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.JSONable;
import model.data.KeygroupID;

public class KeygroupIDTest {

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
	public void testJSON() {
		KeygroupID kg = new KeygroupID("smartlight", "h1", "brightness");
		String json = JSONable.toJSON(kg);
		assertEquals(kg, JSONable.fromJSON(json, KeygroupID.class));
	}

}

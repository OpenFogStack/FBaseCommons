package communication;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.zeromq.ZMQ;

import crypto.CryptoProvider;
import crypto.CryptoProvider.EncryptionAlgorithm;
import model.JSONable;
import model.data.KeygroupID;
import model.messages.Message;

public class DirectReceiverTest {

	private static Logger logger = Logger.getLogger(DirectReceiverTest.class.getName());
	
	private static ExecutorService executor;
	private DirectReceiver handler = null;
	public String secret = "testSecret";
	public EncryptionAlgorithm algorithm = EncryptionAlgorithm.AES;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		executor = Executors.newCachedThreadPool();
	}
	
	@Before
	public void setUp() throws Exception {
		handler = new DirectReceiver("tcp://localhost", 6202, secret, algorithm);
	}

	@After
	public void tearDown() throws Exception {
		handler.stopReception();
		logger.debug("\n");
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		executor.shutdownNow();
	}

	@Test
	public void test() throws InterruptedException, ExecutionException, TimeoutException {
		logger.debug("-------Starting test-------");
		assertEquals(0, handler.getNumberOfReceivedMessages());
		handler.startReceiving();
		Future<?> future = executor.submit(new RequestHelper());
		future.get(5, TimeUnit.SECONDS);
		assertEquals(1, handler.getNumberOfReceivedMessages());
		logger.debug("Finished test.");
	}

class RequestHelper implements Runnable {
	
		@Override
		public void run() {
			KeygroupID keygroupID = new KeygroupID();
			Message m = new Message();
			m.setTextualResponse("TestString");
			ZMQ.Context context = ZMQ.context(1);
			ZMQ.Socket requester = context.socket(ZMQ.REQ);
		    requester.connect(handler.getAddress() + ":" + handler.getPort());
		    logger.info("Sending request.");
		    requester.sendMore(keygroupID.getID());
		    requester.send(CryptoProvider.encrypt(JSONable.toJSON(m), secret, algorithm));
		    String reply = CryptoProvider.decrypt(requester.recvStr(), secret, algorithm);
		    assertEquals("Message logged.", reply);
		    requester.close();
		    context.term();
		}
		
	}
	
}

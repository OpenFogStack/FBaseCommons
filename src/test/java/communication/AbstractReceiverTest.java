package communication;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
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
import org.zeromq.ZMQ.Socket;

import crypto.CryptoProvider;
import crypto.CryptoProvider.EncryptionAlgorithm;
import model.JSONable;
import model.data.KeygroupID;
import model.messages.Envelope;
import model.messages.Message;

public class AbstractReceiverTest {

	private static Logger logger = Logger.getLogger(AbstractReceiverTest.class.getName());

	private static ExecutorService executor;
	private Receiver receiver = null;
	public String secret = "testSecret";
	public EncryptionAlgorithm algorithm = EncryptionAlgorithm.AES;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		executor = Executors.newCachedThreadPool();
	}

	@Before
	public void setUp() throws Exception {
		receiver = new Receiver("tcp://localhost", 6201, secret, algorithm, ZMQ.REP);
	}

	@After
	public void tearDown() throws Exception {
		receiver.stopReception();
		logger.debug("\n");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		executor.shutdownNow();
	}

	@Test
	public void testIsListining() {
		logger.debug("-------Starting testIsListining-------");
		receiver.startReceiving();
		assertTrue(receiver.isReceiving());
		logger.debug("Finished testIsListining.");
	}

	@Test
	public void testStopListining() {
		logger.debug("-------Starting testStopListining-------");
		receiver.startReceiving();
		receiver.stopReception();
		assertFalse(receiver.isReceiving());
		logger.debug("Finished testStopListining.");
	}

	@Test
	public void testMultipleStartListening() {
		logger.debug("-------Starting testMultipleStartListening-------");
		receiver.startReceiving();
		assertNull(receiver.startReceiving());
		logger.debug("Finished testMultipleStartListening.");
	}

//	@Test
	public void testProcessRequest()
			throws InterruptedException, ExecutionException, TimeoutException {
		logger.debug("-------Starting testProcessRequest-------");
		assertEquals(0, receiver.getNumberOfReceivedMessages());
		receiver.startReceiving();
		Future<?> future = executor.submit(new RequestHelper());
		future.get(5, TimeUnit.SECONDS);
		assertEquals(1, receiver.getNumberOfReceivedMessages());
		logger.debug("Finished testProcessRequest.");
	}

	@Test
	public void testProcessMultipleRequest()
			throws InterruptedException, ExecutionException, TimeoutException {
		logger.debug("-------Starting testProcessMultipleRequest-------");
		assertEquals(0, receiver.getNumberOfReceivedMessages());
		receiver.startReceiving();
		Future<?> future = executor.submit(new RequestHelper());
		future.get(5, TimeUnit.SECONDS);
		assertEquals(1, receiver.getNumberOfReceivedMessages());
		future = executor.submit(new RequestHelper());
		future.get(5, TimeUnit.SECONDS);
		assertEquals(2, receiver.getNumberOfReceivedMessages());
		logger.debug("Finished testProcessMultipleRequest.");
	}

	@Test
	public void testParallelRequests() throws InterruptedException {
		int number = 10;
		logger.debug("-------Starting testParallelRequests-------");
		receiver.startReceiving();
		List<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < number; i++) {
			Thread t = new Thread(new RequestHelper());
			threads.add(t);
			t.start();
		}
		for (int i = 0; i < number; i++) {
			threads.get(i).join();
		}
		assertEquals(number, receiver.getNumberOfReceivedMessages());
		logger.debug("Finished testParallelRequests.");
	}

	@Test
	public void testMissingContent()
			throws InterruptedException, ExecutionException, TimeoutException {
		logger.debug("-------Starting testMissingContent-------");
		assertEquals(0, receiver.getNumberOfReceivedMessages());
		receiver.startReceiving();
		Future<?> future = executor.submit(new RequestHelper(true, false));
		future.get(5, TimeUnit.SECONDS);
		assertEquals(0, receiver.getNumberOfReceivedMessages());
		logger.debug("Finished testMissingContent.");
	}

	@Test
	public void testMissingContentCompleteMessage()
			throws InterruptedException, ExecutionException, TimeoutException {
		logger.debug("-------Starting testMissingContentCompleteMessage-------");
		assertEquals(0, receiver.getNumberOfReceivedMessages());
		receiver.startReceiving();
		// missing content
		logger.debug("KeygroupID only");
		Future<?> future = executor.submit(new RequestHelper(true, false));
		future.get(5, TimeUnit.SECONDS);
		assertEquals(0, receiver.getNumberOfReceivedMessages());
		// complete message
		logger.debug("Complete message");
		future = executor.submit(new RequestHelper());
		future.get(5, TimeUnit.SECONDS);
		assertEquals(1, receiver.getNumberOfReceivedMessages());
		// sending the missing content should not increase number of messages
		logger.debug("Content only");
		future = executor.submit(new RequestHelper(false, true));
		future.get(5, TimeUnit.SECONDS);
		assertEquals(1, receiver.getNumberOfReceivedMessages());
		logger.debug("Finished testMissingContentCompleteMessage.");
	}

	@Test
	public void testMissingKeygroupID()
			throws InterruptedException, ExecutionException, TimeoutException {
		logger.debug("-------Starting missingKeygroupID-------");
		assertEquals(0, receiver.getNumberOfReceivedMessages());
		receiver.startReceiving();
		Future<?> future = executor.submit(new RequestHelper(false, true));
		future.get(5, TimeUnit.SECONDS);
		assertEquals(0, receiver.getNumberOfReceivedMessages());
		logger.debug("Finished missingKeygroupID.");
	}

	/*
	 * TODO BUG - The test fails It seems like the socket cannot receive anything after it just got
	 * a keygroupID
	 */
//	@Test
	public void testMissingKeygroupIDCompleteMessage()
			throws InterruptedException, ExecutionException, TimeoutException {
		logger.debug("-------Starting testMissingKeygroupIDCompleteMessage-------");
		assertEquals(0, receiver.getNumberOfReceivedMessages());
		receiver.startReceiving();
		// missing keygroupID
		Future<?> future = executor.submit(new RequestHelper(false, true));
		try {
			future.get(2, TimeUnit.SECONDS);
		} catch (TimeoutException e) {
			// we expect a timeout here
		}
		assertEquals(0, receiver.getNumberOfReceivedMessages());
		// complete message
		logger.debug("Sending complete message");
		Future<?> future2 = executor.submit(new RequestHelper(false, false));
		future2.get(5, TimeUnit.SECONDS);
		assertEquals(1, receiver.getNumberOfReceivedMessages());
		// sending the missing keygroupID should not increase number of messages
		future2 = executor.submit(new RequestHelper(true, false));
		future2.get(5, TimeUnit.SECONDS);
		assertEquals(1, receiver.getNumberOfReceivedMessages());
		logger.debug("Finished testMissingKeygroupIDCompleteMessage.");
	}

	class Receiver extends AbstractReceiver {

		public Receiver(String address, int port, String secret, EncryptionAlgorithm algorithm,
				int receiverType) {
			super(address, port, receiverType);
		}

		@Override
		protected void interpreteReceivedEnvelope(Envelope envelope, Socket responseSocket) {
			envelope.getMessage().decryptFields(secret, algorithm);
			logger.debug("Received envelope " + envelope.getKeygroupID() + " - "
					+ envelope.getMessage().getContent());
			// WARNING: if tests fail because of timeouts, the following might have failed
			if (!"\"Test Message\"".equals(envelope.getMessage().getContent())) {
				fail("Message received does not equal message send");
			}
			responseSocket.send(CryptoProvider.encrypt("received", secret, algorithm));
		}

	}

	class RequestHelper implements Runnable {

		private boolean sendKeygroupID = true;
		private boolean sendContent = true;

		public RequestHelper() {

		}

		public RequestHelper(boolean keygroupID, boolean content) {
			this.sendKeygroupID = keygroupID;
			this.sendContent = content;
		}

		@Override
		public void run() {
			ZMQ.Context context = ZMQ.context(1);
			ZMQ.Socket requester = context.socket(ZMQ.REQ);
			Message m = new Message();
			m.setContent("\"Test Message\"");
			requester.connect(receiver.getAddress() + ":" + receiver.getPort());
			if (this.sendKeygroupID) {
				logger.info("Sending keygroupID.");
				requester.sendMore(new KeygroupID("app", "tenant", "group").getID());
			}
			if (this.sendContent) {
				logger.info("Sending content.");
				m.encryptFields(secret, algorithm);
				requester.send(JSONable.toJSON(m));
			}
			if (sendKeygroupID && sendContent) {
				String reply = CryptoProvider.decrypt(requester.recvStr(), secret, algorithm);
				assertEquals("received", reply);
			}
			requester.close();
			context.term();
		}

	}
}

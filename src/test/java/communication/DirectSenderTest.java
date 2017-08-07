package communication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import model.messages.Envelope;
import model.messages.Message;

public class DirectSenderTest {

	private static Logger logger = Logger.getLogger(DirectSenderTest.class.getName());

	private static ExecutorService executor;
	private DirectSender sender = null;
	public String secret = "testSecret";
	public EncryptionAlgorithm algorithm = EncryptionAlgorithm.AES;
	public Envelope e1 = new Envelope(new KeygroupID("a", "b", "c"), new Message());

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		executor = Executors.newCachedThreadPool();
	}

	@Before
	public void setUp() throws Exception {
		sender = new DirectSender("tcp://localhost", 6204);
	}

	@After
	public void tearDown() throws Exception {
		sender.shutdown();
		logger.debug("\n");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		executor.shutdownNow();
	}

	@Test
	public void testShutdown() {
		logger.debug("-------Starting testShutdown-------");
		sender.shutdown();
		assertTrue(sender.isShutdown());
		logger.debug("Finished testShutdown.");
	}

	@Test
	public void testSendOne() throws InterruptedException, ExecutionException, TimeoutException {
		logger.debug("-------Starting testSendOne-------");
		Future<?> future = executor.submit(new ReceiveHelper(e1));
		Thread.sleep(200);
		String reply = sender.send(e1, secret, algorithm);
		future.get(5, TimeUnit.SECONDS);
		assertEquals(reply, e1.getKeygroupID().getID());
		logger.debug("Finished testSendOne.");
	}

	@Test
	public void testSendTwo() throws InterruptedException, ExecutionException, TimeoutException {
		logger.debug("-------Starting testSendTwo-------");
		Future<?> future = executor.submit(new ReceiveHelper(e1, 2));
		Thread.sleep(200);
		String reply1 = sender.send(e1, secret, algorithm);
		String reply2 = sender.send(e1, secret, algorithm);
		future.get(5, TimeUnit.SECONDS);
		assertEquals(reply1, e1.getKeygroupID().getID());
		assertEquals(reply2, e1.getKeygroupID().getID());
		logger.debug("Finished testSendTwo.");
	}

	class ReceiveHelper implements Runnable {

		private Envelope e = null;
		int messages;

		public ReceiveHelper(Envelope e, int messages) {
			this.e = e;
			this.messages = messages;
		}

		public ReceiveHelper(Envelope e) {
			this.e = e;
			this.messages = 1;
		}

		@Override
		public void run() {
			ZMQ.Context context = ZMQ.context(1);
			ZMQ.Socket receiver = context.socket(ZMQ.REP);
			receiver.bind(sender.getAddress() + ":" + sender.getPort());
			for (int i = 1; i <= messages; i++) {
				logger.debug("Waiting for envelope");
				String keygroupID = receiver.recvStr();
				logger.debug("Keygroup: " + keygroupID);
				String content = CryptoProvider.decrypt(receiver.recvStr(), secret, algorithm);
				Message m = JSONable.fromJSON(content, Message.class);
				logger.info("Message: " + m.getContent());
				receiver.send(CryptoProvider.encrypt(keygroupID, secret, algorithm));
				assertEquals(e.getMessage(), m);
				logger.debug("Finished loop " + i + " of " + messages);
			}
			logger.debug("Closing receiver");
			receiver.close();
			context.term();
		}

	}

}

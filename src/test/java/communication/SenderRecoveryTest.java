package communication;

import org.apache.log4j.Logger;

import crypto.CryptoProvider.EncryptionAlgorithm;
import model.data.KeygroupID;
import model.messages.Envelope;
import model.messages.Message;

/**
 * 
 * This experiment tests whether the {@link DirectReceiver} will still receive messages if the
 * {@link DirectSender} crashed and restarted.
 * 
 * @author jonathanhasenburg
 *
 */
public class SenderRecoveryTest {

	private static Logger logger = Logger.getLogger(SenderRecoveryTest.class.getName());

	private static String address = "tcp://localhost";
	private static int port = 5689;

	private static String secret = "secretABD";
	private static EncryptionAlgorithm algorithm = EncryptionAlgorithm.AES;

	public static void main(String[] args) {
		Envelope envelope = new Envelope(new KeygroupID("smartlight", "h1", "brightness"),
				new Message(null, "Testcontent"));

		DirectSender2 sender = new DirectSender2(address, port);
		DirectReceiver receiver = new DirectReceiver(address, port, secret, algorithm);
		receiver.startReceiving();

		logger.debug("MESSAGE 1");
		logger.debug(sender.send(envelope, secret, algorithm));
		logger.debug("Number of received messages: " + receiver.getNumberOfReceivedMessages());

		sender.shutdown();
		sender = new DirectSender2(address, port);
		
		logger.debug("MESSAGE 2");
		logger.debug(sender.send(envelope, secret, algorithm));
		logger.debug("Number of received messages: " + receiver.getNumberOfReceivedMessages());

		sender = new DirectSender2(address, port);
		logger.debug("MESSAGE 3");
		logger.debug(sender.send(envelope, secret, algorithm));
		logger.debug("Number of received messages: " + receiver.getNumberOfReceivedMessages());
		
		receiver.stopReception();

	}

}

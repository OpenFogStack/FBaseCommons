package communication;

import org.apache.log4j.Logger;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

import crypto.CryptoProvider;
import crypto.CryptoProvider.EncryptionAlgorithm;
import model.messages.Envelope;

public class DirectReceiver extends AbstractReceiver {

	private static Logger logger = Logger.getLogger(DirectReceiver.class.getName());

	private String secret;
	private EncryptionAlgorithm algorithm;
	
	public DirectReceiver(String address, int port, String secret, EncryptionAlgorithm algorithm) {
		super(address, port, ZMQ.REP);
		this.secret = secret;
		this.algorithm = algorithm;
	}

	@Override
	protected void interpreteReceivedEnvelope(Envelope envelope, Socket responseSocket) {
		logger.debug("Interpreting message.");
		try {
			// Code to interpret message
			logger.debug("The fields of the received envelope are encrypted: "
					+ envelope.getMessage().isEncrypted());
			responseSocket.send(CryptoProvider.encrypt("Message interpreted.", secret, algorithm));
		} catch (IllegalArgumentException e) {
			logger.warn(e.getMessage());
			responseSocket.send(CryptoProvider.encrypt(e.getMessage(), secret, algorithm));
		}

	}

}

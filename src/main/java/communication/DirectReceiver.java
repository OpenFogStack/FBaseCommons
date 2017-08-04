package communication;

import org.apache.log4j.Logger;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

import crypto.CryptoProvider;
import crypto.CryptoProvider.EncryptionAlgorithm;
import model.messages.Envelope;

public class DirectReceiver extends AbstractReceiver {

	private static Logger logger = Logger.getLogger(DirectReceiver.class.getName());
	
	public DirectReceiver(String address, int port, String secret, EncryptionAlgorithm algorithm) {
		super(address, port, secret, algorithm, ZMQ.REP);
	}

	@Override
	protected void interpreteReceivedEnvelope(Envelope envelope, Socket responseSocket) {
		logger.debug("Interpreting message.");
		try {
			// Code to interpret message TODO
		
			responseSocket.send(CryptoProvider.encrypt("Message logged.", secret, algorithm));
		} catch (IllegalArgumentException e) {
			logger.warn(e.getMessage());
			responseSocket.send(CryptoProvider.encrypt(e.getMessage(), secret, algorithm));
		}
		
	}

}

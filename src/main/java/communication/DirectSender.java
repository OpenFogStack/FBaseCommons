package communication;

import org.apache.log4j.Logger;
import org.zeromq.ZMQ;

import crypto.CryptoProvider;
import crypto.CryptoProvider.EncryptionAlgorithm;
import model.JSONable;
import model.messages.Envelope;

/**
 * Sends requests to designated receivers.
 * 
 * @author jonathanhasenburg
 *
 */
public class DirectSender extends AbstractSender {

	private static Logger logger = Logger.getLogger(DirectSender.class.getName());

	/**
	 * Initializes the DirectSender, it then can be used without further modifications.
	 */
	public DirectSender(String address, int port) {
		super(address, port, ZMQ.REQ);
	}

	/**
	 * Sends an envelope to the specified address.
	 * 
	 * @param envelope
	 * @return the response
	 */
	@Override
	public String send(Envelope envelope, String secret, EncryptionAlgorithm algorithm) {
		logger.debug("Sending envelope with keygroup " + envelope.getKeygroupID());
		sender.sendMore(envelope.getKeygroupID().getID());
		sender.send(
				CryptoProvider.encrypt(JSONable.toJSON(envelope.getMessage()), secret, algorithm));
		return CryptoProvider.decrypt(sender.recvStr(), secret, algorithm);
	}

}

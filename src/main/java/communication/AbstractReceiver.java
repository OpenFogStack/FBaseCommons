package communication;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.zeromq.ZMQ;
import org.zeromq.ZMQException;

import crypto.CryptoProvider.EncryptionAlgorithm;
import model.JSONable;
import model.data.ConfigID;
import model.data.KeygroupID;
import model.data.NodeID;
import model.messages.Envelope;
import model.messages.Message;

/**
 * Abstract class for {@link Subscriber} and {@link DirectReceiver}
 * 
 * @author jonathanhasenburg
 *
 */
public abstract class AbstractReceiver {

	private static Logger logger = Logger.getLogger(AbstractReceiver.class.getName());

	/**
	 * The address used for the reception of messages.
	 */
	private final String address;

	/**
	 * The port used for the reception of messages.
	 */
	private final int port;

	/**
	 * The number of messages that have been received until now
	 */
	private int numberOfReceivedMessages = 0;

	/**
	 * The executor that is used to execute the runnable which is used for reception.
	 */
	private final ExecutorService executor = Executors.newSingleThreadExecutor();

	/**
	 * Future of the runnable which is used for reception.
	 */
	private Future<?> runnableFuture = null;

	/**
	 * The receiver type. Possible values are ZMQ.SUB and ZMQ.REP.
	 */
	private int receiverType;

	/**
	 * If the used socket is a subscriber, the filter might be used to only receive messages
	 * with a specific configID, e.g a specific keygroupID or nodeID.
	 */
	protected ConfigID filterID = null;

	private ZMQ.Context context = null;
	private ZMQ.Socket socket = null;

	public AbstractReceiver(String address, int port, int receiverType) {
		this.address = address;
		this.port = port;
		if (receiverType != ZMQ.SUB && receiverType != ZMQ.REP) {
			throw new IllegalArgumentException("Receiver type " + receiverType + " is not valid.");
		}
		this.receiverType = receiverType;
	}

	/**
	 * @return {@link #address}
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return {@link #port}
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @return {@link #numberOfReceivedMessages}
	 */
	public int getNumberOfReceivedMessages() {
		return numberOfReceivedMessages;
	}

	/**
	 * Increments {@link #numberOfReceivedMessages} by one.
	 */
	protected void incrementNumberOfReceivedMessages() {
		numberOfReceivedMessages++;
	}

	/**
	 * @return {@link #filterID}
	 */
	public ConfigID getFilterID() {
		return filterID;
	}

	/**
	 * Starts receiving and interpreting incoming envelopes..
	 * 
	 * @return a Future instance if reception was not running before and operation successful,
	 *         <code>null</code> otherwise
	 */
	public Future<?> startReceiving() {
		if (runnableFuture != null && !runnableFuture.isDone()) {
			logger.error("Did not start reception because already running.");
			return null;
		}
		Future<Boolean> fut = executor.submit(new Callable<Boolean>() {

			@Override
			public Boolean call() throws Exception {
				if (executor.isShutdown())
					return false;
				if (receiverType == ZMQ.SUB) {
					context = ZMQ.context(1);
					socket = context.socket(ZMQ.SUB);
					socket.connect(getAddress() + ":" + getPort());
					if (filterID != null) {
						socket.subscribe(filterID.getID().getBytes());
					} else {
						socket.subscribe("".getBytes());
					}
					return true;
				} else if (receiverType == ZMQ.REP) {
					context = ZMQ.context(1);
					socket = context.socket(ZMQ.REP);
					socket.bind(getAddress() + ":" + getPort());
					return true;
				} else {
					logger.error(receiverType + " is not a valid socket type");
					return false;
				}
			}

		});

		try {
			if (!fut.get()) {
				logger.error("Initialization failed.");
				return null;
			}
		} catch (Exception e) {
			logger.error("Initialization was interrupted.");
			e.printStackTrace();
			return null;
		}
		// init was successful
		runnableFuture = executor.submit(new Runnable() {
			@Override
			public void run() {

				while (!Thread.currentThread().isInterrupted()) {
					Envelope envelope = new Envelope(null, null);
					boolean envelopeFine = true;

					try {
						boolean more = true;
						while (more) {
							String s = socket.recvStr();
							if (envelope.getConfigID() == null) {
								envelope.setKeygroupID(KeygroupID.createFromString(s));
								if (envelope.getKeygroupID() != null) {
									logger.debug("Received keygroupID: " + envelope.getKeygroupID());
								} else {
									envelope.setNodeID(new NodeID(s));
									logger.debug("Received nodeID: " + envelope.getNodeID());
								}
							} else if (envelope.getMessage() == null) {
								logger.debug("Received message");
								envelope.setMessage(JSONable.fromJSON(s, Message.class));
							} else {
								logger.error("Received more multipart messages than expected, "
										+ "dismissing: " + s);
								envelopeFine = false;
							}
							more = socket.hasReceiveMore();
						}
					} catch (ZMQException e) {
						logger.debug("Context was terminated, thread is dying.");
						return;
					}

					if (envelope.getConfigID() == null || envelope.getMessage() == null) {
						logger.error("Envelope incomplete");
					} else if (!envelopeFine) {
						logger.error("Envelope broken");
					} else {
						incrementNumberOfReceivedMessages();
						logger.debug("Received complete message, configID: "
								+ envelope.getConfigID().getID());
						interpreteReceivedEnvelope(envelope, socket);
					}
				}
			}
		});
		logger.info("Started receiving incoming envelopes.");
		return runnableFuture;
	}

	/**
	 * Stops the reception of envelopes immediately.
	 */
	public void stopReception() {
		executor.shutdown();
		if (runnableFuture != null) {
			runnableFuture = null;
			socket.close();
			context.term();
		}
		executor.shutdownNow();
		logger.info("Reception of incoming envelopes is stopped."
				+ (executor.isTerminated() ? "" : " Some tasks may still be running."));
	}

	/**
	 * Checks whether reception of envelopes is currently running.
	 * 
	 * @return <code>true</code> if currently receiving, false otherwise
	 */
	public boolean isReceiving() {
		if (runnableFuture != null) {
			return !runnableFuture.isDone();
		}
		return false;
	}

	/**
	 * Gets automatically called after a complete envelope was received. The fields of the
	 * stored {@link Message} might be encrypted, in which case
	 * {@link Message#encryptFields(String, EncryptionAlgorithm)} should be called before
	 * using the actual content.
	 * 
	 * @param envelope - the received envelope
	 * @param responseSocket - socket used to response. Cannot be used if of type ZMQ.SUB
	 */
	protected abstract void interpreteReceivedEnvelope(Envelope envelope,
			ZMQ.Socket responseSocket);

}

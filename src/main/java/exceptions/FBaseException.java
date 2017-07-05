/**
 * 
 */
package exceptions;

/**
 * Super class of all checked exceptions thrown by FBase components.
 * 
 * @author jonathanhasenburg
 * @author Dave
 *
 */
public class FBaseException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public FBaseException() {

	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public FBaseException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FBaseException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public FBaseException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public FBaseException(Throwable cause) {
		super(cause);
	}

}

package net.sf.jxls.exception;

/**
 * @author Leonid Vysochyn
 */
public class TaglibRegistrationException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public TaglibRegistrationException(String message) {
        super(message);
    }

    public TaglibRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaglibRegistrationException(Throwable cause) {
        super(cause);
    }

    public TaglibRegistrationException() {
    }
}

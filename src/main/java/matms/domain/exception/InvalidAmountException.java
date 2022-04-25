package matms.domain.exception;

public class InvalidAmountException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8282301105239435502L;

	public InvalidAmountException(String errorMessage) {
		super(errorMessage);
	}
	
}

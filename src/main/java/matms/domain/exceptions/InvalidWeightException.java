package matms.domain.exceptions;

public class InvalidWeightException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854657117584601794L;

	public InvalidWeightException(String errorMessage) {
		super(errorMessage);
	}
}

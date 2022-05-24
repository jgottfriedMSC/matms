package matms.domain.exceptions;

public class NoWinnerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1257564172498266477L;

	public NoWinnerException(String errorMessage) {
		super(errorMessage);
	}
}

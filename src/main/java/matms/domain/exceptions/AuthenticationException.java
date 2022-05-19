package matms.domain.exceptions;

public class AuthenticationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3800793825321696873L;

	public AuthenticationException(String errorMessage) {
		super(errorMessage);
	}
	
}

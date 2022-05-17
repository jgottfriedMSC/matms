package matms.domain.exceptions;

public class UserAlreadyExistsException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7615411957045163663L;

	public UserAlreadyExistsException(String errorMessage) {
		super(errorMessage);
	}
}

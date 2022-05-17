package matms.domain.exceptions;

public class UserNotAllowedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1445247719021149154L;

	public UserNotAllowedException(String errorMessage) {
		super(errorMessage);
	}
	
}

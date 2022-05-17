package matms.domain.exceptions;

public class UserNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2617157592646613357L;

	public UserNotFoundException(String errorMessage)  {
		super(errorMessage);
	}
}

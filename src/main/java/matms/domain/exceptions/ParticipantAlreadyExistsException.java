package matms.domain.exceptions;

public class ParticipantAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3247919033694969106L;

	public ParticipantAlreadyExistsException(String errorMessage) {
		super(errorMessage);
	}
	
}

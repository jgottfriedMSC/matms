package matms.domain.exceptions;

public class ParticipantNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8441368023696511742L;

	public ParticipantNotFoundException(String errorMessage) {
		super(errorMessage);
	}
	
}

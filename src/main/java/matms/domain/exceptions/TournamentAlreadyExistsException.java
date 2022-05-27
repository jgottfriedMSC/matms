package matms.domain.exceptions;

public class TournamentAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8292925159629074158L;

	public TournamentAlreadyExistsException(String errorMessage) {
		super(errorMessage);
	}
}

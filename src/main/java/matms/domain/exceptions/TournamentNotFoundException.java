package matms.domain.exceptions;

public class TournamentNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3537561219731447082L;

	public TournamentNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}

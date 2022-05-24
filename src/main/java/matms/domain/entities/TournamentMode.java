package matms.domain.entities;

import matms.domain.exceptions.NoWinnerException;

public interface TournamentMode {

	int calculateNumberOfMatches();
	
	void playRound(Round round);
	
	Round nextRound();
	
	Round getCurrentRound();
	
	Participant getWinner() throws NoWinnerException;
	
	boolean checkIfThereIsWinner();
}

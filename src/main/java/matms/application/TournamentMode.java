package matms.application;

import matms.domain.entities.Participant;
import matms.domain.entities.Round;
import matms.domain.exceptions.NoWinnerException;

public interface TournamentMode {

	int calculateNumberOfMatches();
	
	void playRound(Round round);
	
	Round nextRound();
	
	Round getCurrentRound();
	
	Participant getWinner() throws NoWinnerException;
	
	boolean checkIfThereIsWinner();
}

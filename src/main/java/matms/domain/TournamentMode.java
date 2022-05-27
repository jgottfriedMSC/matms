package matms.application;

import java.math.BigInteger;

import matms.domain.entities.Participant;
import matms.domain.entities.Round;
import matms.domain.exceptions.NoWinnerException;

public interface TournamentMode {

	BigInteger calculateNumberOfMatches();
	
	void playRound(Round round);
	
	Round nextRound();
	
	Round getCurrentRound();
	
	Participant getWinner() throws NoWinnerException;
	
	boolean checkIfThereIsWinner();
}

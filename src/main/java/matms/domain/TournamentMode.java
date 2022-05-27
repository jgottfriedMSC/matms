package matms.domain;

import java.math.BigInteger;
import java.util.Map;

import matms.domain.entities.Participant;
import matms.domain.exceptions.NoWinnerException;
import matms.domain.exceptions.TournamentNotFoundException;

public interface TournamentMode {

	BigInteger calculateNumberOfMatches() throws TournamentNotFoundException;
	
	void playRound(Round round) throws TournamentNotFoundException;
	
	Round nextRound() throws TournamentNotFoundException;
	
	Round getCurrentRound();
	
	Participant getWinner() throws NoWinnerException, TournamentNotFoundException;
	
	boolean checkIfThereIsWinner() throws TournamentNotFoundException;
	
	Map<Participant, Integer> getParticipantPoints();
}

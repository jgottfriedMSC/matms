package matms.adapters;

import java.math.BigInteger;
import java.util.Map;

import matms.domain.Round;
import matms.domain.TournamentMode;
import matms.domain.entities.Participant;
import matms.domain.exceptions.NoWinnerException;
import matms.domain.exceptions.TournamentNotFoundException;

public class RoundService {

	private TournamentMode tournamentMode;
	private BigInteger numberOfRounds;

	public RoundService(TournamentMode tournamentMode) throws TournamentNotFoundException {
		this.tournamentMode = tournamentMode;
		this.numberOfRounds = tournamentMode.calculateNumberOfMatches();
	}

	public void playRound(Round round) throws TournamentNotFoundException {
		tournamentMode.playRound(round);
	}
	
	public Round nextRound() throws TournamentNotFoundException {
		return tournamentMode.nextRound();
	}

	public Participant getTournamentWinner() throws NoWinnerException, TournamentNotFoundException {
		return tournamentMode.getWinner();
	}


	public BigInteger getNumberOfRounds() {
		return numberOfRounds;
	}
	
	public Round getCurrentRound() {
		return tournamentMode.getCurrentRound();
	}
	
	public boolean checkIfThereIsWinner() throws TournamentNotFoundException {
		return tournamentMode.checkIfThereIsWinner();
	}
	
	public Map<Participant, Integer> getParticipantPoints() {
		return tournamentMode.getParticipantPoints();
	}
	
	public TournamentMode getTournamentMode() {
		return tournamentMode;
	}

}

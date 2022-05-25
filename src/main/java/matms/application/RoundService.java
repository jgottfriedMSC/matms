package matms.application;

import java.math.BigInteger;

import matms.domain.entities.Participant;
import matms.domain.entities.Round;
import matms.domain.exceptions.NoWinnerException;

public class RoundService {

	private TournamentMode tournamentMode;
	private BigInteger numberOfRounds;

	public RoundService(TournamentMode tournamentMode) {
		this.tournamentMode = tournamentMode;
		this.numberOfRounds = tournamentMode.calculateNumberOfMatches();
	}

	public void playRound(Round round) {
		tournamentMode.playRound(round);
	}
	
	public Round nextRound() {
		return tournamentMode.nextRound();
	}

	public Participant getTournamentWinner() throws NoWinnerException {
		return tournamentMode.getWinner();
	}


	public BigInteger getNumberOfRounds() {
		return numberOfRounds;
	}
	
	public Round getCurrentRound() {
		return tournamentMode.getCurrentRound();
	}
	
	public boolean checkIfThereIsWinner() {
		return tournamentMode.checkIfThereIsWinner();
	}
	

}

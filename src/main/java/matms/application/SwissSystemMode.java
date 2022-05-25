package matms.application;

import java.math.BigInteger;
import java.util.List;

import matms.abstraction.MathUtils;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.Participant;
import matms.domain.entities.Round;
import matms.domain.exceptions.NoWinnerException;

public class SwissSystemMode implements TournamentMode {

	private MartialArtsTournament tournament;
	
	public SwissSystemMode(MartialArtsTournament tournament) {
		this.tournament = tournament;
	}

	@Override
	public BigInteger calculateNumberOfMatches() {
		return BigInteger.valueOf((long) ((tournament.getParticipants().size() / 2) * MathUtils.customLog(2, tournament.getParticipants().size()))); // n/2 * log_2(n);
	}

	@Override
	public void playRound(Round round) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Round nextRound() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Participant getWinner() throws NoWinnerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Round getCurrentRound() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkIfThereIsWinner() {
		// TODO Auto-generated method stub
		return false;
	}
	
}

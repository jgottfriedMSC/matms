package matms.domain.entities;

import java.util.List;

import matms.abstraction.MathUtils;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.exceptions.NoWinnerException;

public class RoundRobinMode implements TournamentMode {

	private MartialArtsTournament tournament;
	
	public RoundRobinMode(MartialArtsTournament tournament) {
		this.tournament = tournament;
	}
	
	@Override
	public int calculateNumberOfMatches() {
		return (MathUtils.factCalculator(tournament.getParticipants().size())) / (MathUtils.factCalculator(2)) * MathUtils.factCalculator(tournament.getParticipants().size() - 2); // Binomialkoeffizient;
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

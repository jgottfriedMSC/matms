package matms.application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import matms.abstraction.MathUtils;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.Participant;
import matms.domain.entities.Round;
import matms.domain.exceptions.NoWinnerException;
import matms.domain.valueobjects.Match;

public class RoundRobinMode implements TournamentMode {

	private MartialArtsTournament tournament;
	private Round currentRound;
	
	public RoundRobinMode(MartialArtsTournament tournament) {
		this.tournament = tournament;
		this.currentRound = initializeRound();
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
		if (tournament.getParticipants().size() == 1) {
			Iterator<Map.Entry<String, Participant>> it = tournament.getParticipants().entrySet().iterator();
			if (it.hasNext()) {
				return it.next().getValue();
			} else {
				throw new NoWinnerException("There is no winner yet!");
			}
		} else {
			throw new NoWinnerException("There is no winner yet!");
		}
	}

	@Override
	public Round getCurrentRound() {
		return currentRound;
	}

	@Override
	public boolean checkIfThereIsWinner() {
		return tournament.getParticipants().size() > 1;
	}
	
	private Round initializeRound() {
		Iterator<Map.Entry<String, Participant>> it = tournament.getParticipants().entrySet().iterator();
		List<Match> matches = new ArrayList<>();

		while (it.hasNext()) {
			Participant participant = it.next().getValue();
			Participant opponent = it.next().getValue();
			matches.add(new Match(participant, opponent));
		}
		return new Round(matches);
	}


}

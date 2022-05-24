package matms.application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import matms.abstraction.MathUtils;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.Participant;
import matms.domain.entities.Round;
import matms.domain.exceptions.NoWinnerException;
import matms.domain.valueobjects.Match;

public class RoundRobinMode implements TournamentMode {

	private MartialArtsTournament tournament;
	private Round currentRound;
	private int numberOfMatches;
	private int currentMatch = 0;
	private Map<Participant, Integer> participantPoints = new HashMap<>();
	
	public RoundRobinMode(MartialArtsTournament tournament) {
		this.tournament = tournament;
		this.currentRound = initializeRound();
		this.numberOfMatches = calculateNumberOfMatches();
	}
	
	@Override
	public int calculateNumberOfMatches() {
		return (MathUtils.factCalculator(tournament.getParticipants().size())) / ((MathUtils.factCalculator(2)) * MathUtils.factCalculator(tournament.getParticipants().size() - 2)); // Binomialkoeffizient;
	}

	@Override
	public void playRound(Round round) {
		for (Match match : round.getMatches()) {
			match.defineLoser(match.getParticipant());
			Integer newPoint = participantPoints.get(match.getOpponent()) + 1;
			participantPoints.put(match.getOpponent(), newPoint);
			match.getOpponent().addToPlayedAgainst(match.getParticipant());
			match.getParticipant().addToPlayedAgainst(match.getOpponent());
			currentMatch++;
		}
		
	}

	@Override
	public Round nextRound() {
		Iterator<Map.Entry<String, Participant>> it = tournament.getParticipants().entrySet().iterator();
		List<Match> matches = new ArrayList<>();

		if ((tournament.getParticipants().size() % 2) != 0) {
			for (int i = 0; i < tournament.getParticipants().size() - 1; i += 2) {
				Participant participant = it.next().getValue();
				Participant opponent = it.next().getValue();
				if (participant.getPlayedAgainst().contains(opponent)) {
					if (it.hasNext()) {
						opponent = it.next().getValue();
						matches.add(new Match(participant, opponent));
					}
				}
			}
		} else {
			while (it.hasNext()) {
				// Immer nur einen Nehmen und die Liste durchgehen, schauen welcher noch nicht dran war
				Participant participant = it.next().getValue();
				for (Participant p : tournament.getParticipants().values()) {
					if (!participant.getPlayedAgainst().contains(p) && !participant.equals(p)) {
						matches.add(new Match(participant, p));
						break;
					}
				}
				// nimm immer die Participants aus der Liste sobald es ein Match gibt
			}

		}
		this.currentRound = new Round(matches);
		return this.currentRound;
	}

	@Override
	public Participant getWinner() throws NoWinnerException {
		int max = Collections.max(participantPoints.values());
		
		for (Entry<Participant, Integer> entry : participantPoints.entrySet()) {
			if (max == entry.getValue()) {
				return entry.getKey();
			} else {
				throw new NoWinnerException("There is no winner yet!");
			}
		}
		
		throw new NoWinnerException("There is no winner yet!");
	}

	@Override
	public Round getCurrentRound() {
		return currentRound;
	}

	@Override
	public boolean checkIfThereIsWinner() {
		return currentMatch != numberOfMatches;
	}
	
	private Round initializeRound() {
		Iterator<Map.Entry<String, Participant>> it = tournament.getParticipants().entrySet().iterator();
		List<Match> matches = new ArrayList<>();

		while (it.hasNext()) {
			Participant participant = it.next().getValue();
			Participant opponent = it.next().getValue();
			participantPoints.put(participant, 0);
			participantPoints.put(opponent, 0);
			matches.add(new Match(participant, opponent));
		}
		return new Round(matches);
	}


}

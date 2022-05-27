package matms.application;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import matms.abstraction.MathUtils;
import matms.domain.Round;
import matms.domain.TournamentMode;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.Participant;
import matms.domain.exceptions.NoWinnerException;
import matms.domain.exceptions.TournamentNotFoundException;
import matms.domain.valueobjects.Match;

public class RoundRobinMode implements TournamentMode {

	private Optional<MartialArtsTournament> tournament;
	private Round currentRound;
	private BigInteger numberOfMatches;
	private int currentMatch = 0;
	private Map<Participant, Integer> participantPoints = new HashMap<>();

	public RoundRobinMode(Optional<MartialArtsTournament> tournament) throws TournamentNotFoundException {
		this.tournament = tournament;
		this.numberOfMatches = calculateNumberOfMatches();
		initializePoints();
	}

	@Override
	public BigInteger calculateNumberOfMatches() throws TournamentNotFoundException {
		if (tournament.isPresent()) {
			return MathUtils.binomialCoefficient(BigInteger.valueOf(tournament.get().getParticipants().size()),
					BigInteger.valueOf(2)); // Binomialkoeffizient;
		} else {
			throw new TournamentNotFoundException("Tournament not found!");
		}
	}

	@Override
	public void playRound(Round round) {
		for (Match match : round.getMatches()) {
			// Loser hardcoded. normally User Input here.
			match.defineLoser(match.getParticipant());
			Integer newPoint = participantPoints.get(match.getOpponent()) + 1;
			participantPoints.put(match.getOpponent(), newPoint);
			match.getOpponent().addToPlayedAgainst(match.getParticipant());
			match.getParticipant().addToPlayedAgainst(match.getOpponent());
			match.getOpponent().setMatch(false);
			match.getParticipant().setMatch(false);
			currentMatch++;
		}

	}

	private void initializePoints() throws TournamentNotFoundException {
		for (Entry<String, Participant> entry : tournament.get().getParticipants().entrySet()) {
			participantPoints.put(entry.getValue(), 0);
		}

	}

	@Override
	public Round nextRound() throws TournamentNotFoundException {
		Iterator<Map.Entry<String, Participant>> it = tournament.get().getParticipants().entrySet().iterator();
		List<Match> matches = new ArrayList<>();

		while (it.hasNext()) {
			// Immer nur einen Nehmen und die Liste durchgehen, schauen welcher noch nicht
			// dran war
			Participant participant = it.next().getValue();
			for (Participant p : tournament.get().getParticipants().values()) {
				if (!participant.getPlayedAgainst().contains(p) && !participant.equals(p) && !participant.hasMatch()
						&& !p.hasMatch()) {
					matches.add(new Match(participant, p));
					participant.setMatch(true);
					p.setMatch(true);
					break;
				}
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
		return currentMatch != numberOfMatches.intValue();
	}

	@Override
	public Map<Participant, Integer> getParticipantPoints() {
		return participantPoints;
	}

}

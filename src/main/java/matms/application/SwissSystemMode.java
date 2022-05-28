package matms.application;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import matms.abstraction.MathUtils;
import matms.domain.Round;
import matms.domain.TournamentMode;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.Participant;
import matms.domain.exceptions.TournamentNotFoundException;
import matms.domain.valueobjects.Match;

public class SwissSystemMode implements TournamentMode {

	private Optional<MartialArtsTournament> tournament;
	private Round currentRound;
	private BigInteger numberOfMatches;
	private int currentMatch = 0;
	private Map<Participant, Integer> participantPoints = new HashMap<>();
	
	public SwissSystemMode(Optional<MartialArtsTournament> tournament) throws TournamentNotFoundException {
		this.tournament = tournament;
		this.numberOfMatches = calculateNumberOfMatches();
		initializePoints();
	}

	@Override
	public BigInteger calculateNumberOfMatches() throws TournamentNotFoundException {
		if (tournament.isPresent()) {
			return BigInteger.valueOf((long) ((tournament.get().getParticipants().size() / 2) * MathUtils.getInstance().customLog(2, tournament.get().getParticipants().size()))); // n/2 * log_2(n);
		} else {
			throw new TournamentNotFoundException("Tournament not found!");
		}
	}

	@Override
	public void playRound(Round round) {
		for (Match match : round.getMatches()) {
			//Loser hardcoded. normally User Input here.
			match.defineLoser(match.getParticipant());
			Integer newPoint = participantPoints.get(match.getOpponent()) + 1;
			participantPoints.put(match.getOpponent(), newPoint);
			match.getOpponent().addToPlayedAgainst(match.getParticipant());
			match.getParticipant().addToPlayedAgainst(match.getOpponent());
			match.getOpponent().setMatch(false);
			match.getParticipant().setMatch(false);
			currentMatch++;
			if (!checkIfThereIsWinner()) {
				break;
			}
		}
	}
	
	private void initializePoints() throws TournamentNotFoundException {
		if (tournament.isPresent()) {
			for (Entry<String, Participant> entry : tournament.get().getParticipants().entrySet()) {
				participantPoints.put(entry.getValue(), 0);
			}
		} else {
			throw new TournamentNotFoundException("Tournament not found!");
		}

	}

	@Override
	public Round nextRound() {
		participantPoints = sortByValue(participantPoints);
		
		Iterator<Map.Entry<Participant, Integer>> it = participantPoints.entrySet().iterator();
		List<Match> matches = new ArrayList<>();
		
		while (it.hasNext()) {
			Participant participant = it.next().getKey();
			for (Participant p : participantPoints.keySet()) {
				if (!participant.getPlayedAgainst().contains(p) && !participant.equals(p) && !participant.hasMatch() && !p.hasMatch()) {
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
	public Participant getWinner() {
		ArrayList<Participant> winner = new ArrayList<>();
		int max = Collections.max(participantPoints.values());
		
		for (Entry<Participant, Integer> entry : participantPoints.entrySet()) {
			if (max == entry.getValue()) {
				winner.add(entry.getKey());
			}
		}
		// little k.o.-system to determine winner if there is a draw
		Iterator<Participant> it = winner.iterator();
		List<Match> matches = new ArrayList<>();
		
		if (winner.size() > 1) {
			if ((winner.size() % 2) != 0) {
				for (int i = 0; i < winner.size() - 1; i += 2) {
					Participant participant = it.next();
					Participant opponent = it.next();
					matches.add(new Match(participant, opponent));
				}
			} else {
				while (it.hasNext()) {
					Participant participant = it.next();
					Participant opponent = it.next();
					matches.add(new Match(participant, opponent));
				}
			}
			this.currentRound = new Round(matches);
			playRound(currentRound);
			getWinner();
		}
		
		return winner.get(0);
		
	}

	@Override
	public Round getCurrentRound() {
		return currentRound;
	}

	@Override
	public boolean checkIfThereIsWinner() {
		return currentMatch != numberOfMatches.intValue();
	}
	
	private <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
		Collections.sort(list, Collections.reverseOrder(Entry.comparingByValue()));
		
		Map<K, V> result = new LinkedHashMap<>();
		for (Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		
		return result;
	}

	@Override
	public Map<Participant, Integer> getParticipantPoints() {
		return participantPoints;
	}
	
}

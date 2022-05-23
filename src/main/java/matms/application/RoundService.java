package matms.application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.Participant;
import matms.domain.entities.TournamentMode;
import matms.domain.exceptions.NoRoundsException;
import matms.domain.exceptions.ParticipantNotFoundException;
import matms.domain.valueobjects.Round;

public class RoundService {

	private List<Round> rounds;
	private TournamentMode tournamentMode;
	private int numberOfRounds;
	private int currentRound;
	private MartialArtsTournament tournament;

	public RoundService(MartialArtsTournament tournament, TournamentMode tournamentMode) {
		this.rounds = new ArrayList<>();
		this.tournament = tournament;
		this.tournamentMode = tournamentMode;
		this.numberOfRounds = tournamentMode.calculateNumberOfRounds();
	}

	// First method every Round
	public List<Round> initializeEncounters() {
		Iterator<Map.Entry<String, Participant>> it = tournament.getParticipants().entrySet().iterator();

		while (it.hasNext()) {
			Participant participant = it.next().getValue();
			Participant opponent = it.next().getValue();
			rounds.add(new Round(participant, opponent));
			currentRound = 0;
		}
		return rounds;
	}

	// Call after initializeEncounters()
	public Participant determineRoundWinner(Round round, Participant winner) throws ParticipantNotFoundException, NoRoundsException {
		Round thisRound = rounds.get(currentRound);
		if (winner.equals(null)) {
			throw new ParticipantNotFoundException("Can't find this Participant");
		} else if (currentRound > rounds.size()) {
			throw new NoRoundsException("There are no rounds left to play!");
		}
		else {
			if (thisRound.getParticipant().equals(winner)) {
				thisRound.defineLoser(thisRound.getOpponent());
				tournament.getParticipants().remove(thisRound.getOpponent().getId().getUuid());
				currentRound++;
				return thisRound.getParticipant();
			} else if (thisRound.getOpponent().equals(winner)) {
				thisRound.defineLoser(thisRound.getParticipant());
				tournament.getParticipants().remove(thisRound.getParticipant().getId().getUuid());
				currentRound++;
				return thisRound.getOpponent();
			} else {
				throw new ParticipantNotFoundException("Can't find this Participant");
			}
		}
	}

	public Participant getWinner() throws ParticipantNotFoundException {
		if (tournament.getParticipants().size() == 1) {
			Iterator<Map.Entry<String, Participant>> it = tournament.getParticipants().entrySet().iterator();
			if (it.hasNext()) return it.next().getValue();
		}
		throw new ParticipantNotFoundException("There is no winner yet!");
	}
	
	public void createRound(Participant participant, Participant opponent) {
		Round round = new Round(participant, opponent);
		rounds.add(round);
	}

	public int getNumberOfRounds() {
		return numberOfRounds;
	}
	
	public List<Round> getRounds() {
		return rounds;
	}
	
	public int getCurrentRound() {
		return currentRound;
	}

}

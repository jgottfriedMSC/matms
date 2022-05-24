package matms.domain.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.exceptions.NoWinnerException;
import matms.domain.valueobjects.Match;

public class KnockoutMode implements TournamentMode {

	private MartialArtsTournament tournament;
	private Round currentRound;

	public KnockoutMode(MartialArtsTournament tournament) {
		this.tournament = tournament;
		this.currentRound = initializeRound();
	}

	@Override
	public int calculateNumberOfMatches() {
		return tournament.getParticipants().size() - 1; // n - 1
	}

	@Override
	public void playRound(Round round) {
		for (Match match : round.getMatches()) {
			match.defineLoser(match.getParticipant());
			tournament.getParticipants().remove(match.getParticipant().getId().getUuid());
		}
	}

	public Round nextRound() {
		Iterator<Map.Entry<String, Participant>> it = tournament.getParticipants().entrySet().iterator();
		List<Match> matches = new ArrayList<>();

		if ((tournament.getParticipants().size() % 2) != 0) {
			for (int i = 0; i < tournament.getParticipants().size() - 1; i += 2) {
				Participant participant = it.next().getValue();
				Participant opponent = it.next().getValue();
				matches.add(new Match(participant, opponent));
			}
		} else {
			while (it.hasNext()) {
				Participant participant = it.next().getValue();
				Participant opponent = it.next().getValue();
				matches.add(new Match(participant, opponent));
			}

		}
		this.currentRound = new Round(matches);
		return this.currentRound;
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
	
	public boolean checkIfThereIsWinner() {
		return tournament.getParticipants().size() > 1;
	}

	@Override
	public Round getCurrentRound() {
		return currentRound;
	}

}

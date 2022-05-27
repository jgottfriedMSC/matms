package matms.application;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import matms.domain.Round;
import matms.domain.TournamentMode;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.Participant;
import matms.domain.exceptions.NoWinnerException;
import matms.domain.exceptions.TournamentNotFoundException;
import matms.domain.valueobjects.Match;

public class KnockoutMode implements TournamentMode {

	private Optional<MartialArtsTournament> tournament;
	private Round currentRound;

	public KnockoutMode(Optional<MartialArtsTournament> tournament) {
		this.tournament = tournament;
	}

	@Override
	public BigInteger calculateNumberOfMatches() throws TournamentNotFoundException {
		if (tournament.isPresent()) {
			return BigInteger.valueOf(tournament.get().getParticipants().size() - 1); // n - 1
		} else {
			throw new TournamentNotFoundException("Tournament not found!");
		}
	}

	@Override
	public void playRound(Round round) throws TournamentNotFoundException {
		if (tournament.isPresent()) {
			for (Match match : round.getMatches()) {
				//Loser hardcoded. normally User Input here.
				match.defineLoser(match.getParticipant());
				tournament.get().getParticipants().remove(match.getParticipant().getId().getUuid());
			}
		} else {
			throw new TournamentNotFoundException("Tournament not found!");
		}

	}

	public Round nextRound() throws TournamentNotFoundException {
		if (tournament.isPresent()) {
			Iterator<Map.Entry<String, Participant>> it = tournament.get().getParticipants().entrySet().iterator();
			List<Match> matches = new ArrayList<>();

			if ((tournament.get().getParticipants().size() % 2) != 0) {
				for (int i = 0; i < tournament.get().getParticipants().size() - 1; i += 2) {
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
		} else {
			throw new TournamentNotFoundException("Tournament not found!");
		}

	}

	@Override
	public Participant getWinner() throws NoWinnerException, TournamentNotFoundException {
		if (tournament.isPresent()) {
			if (tournament.get().getParticipants().size() == 1) {
				Iterator<Map.Entry<String, Participant>> it = tournament.get().getParticipants().entrySet().iterator();
				if (it.hasNext()) {
					return it.next().getValue();
				} else {
					throw new NoWinnerException("There is no winner yet!");
				}
			} else {
				throw new NoWinnerException("There is no winner yet!");
			}
		} else {
			throw new TournamentNotFoundException("Tournament not found!");
		}

	}
	
	public boolean checkIfThereIsWinner() throws TournamentNotFoundException {
		if (tournament.isPresent()) {
			return tournament.get().getParticipants().size() > 1;
		} else {
			throw new TournamentNotFoundException("Tournament not found!");
		}
		
	}

	@Override
	public Round getCurrentRound() {
		return currentRound;
	}

	@Override
	public Map<Participant, Integer> getParticipantPoints() {
		// return empty Hashmap because in KnockoutMode there are no points
		return new HashMap<Participant, Integer>();
	}

}

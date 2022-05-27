package matms.application;

import java.util.Iterator;
import java.util.Optional;

import matms.domain.MartialArt;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.Participant;
import matms.domain.exceptions.TournamentNotFoundException;

public final class TournamentParticipationService {

	private final Optional<MartialArtsTournament> tournament;
	
	public TournamentParticipationService(final Optional<MartialArtsTournament> tournament) {
		this.tournament = tournament;
	}
	
	public void removeUnqualifiedParticipants() throws TournamentNotFoundException {
		if (tournament.isPresent()) {
			Iterator<Participant> iterator = tournament.get().getParticipants().values().iterator();
			while (iterator.hasNext()) {
				Participant p = iterator.next();
				for (MartialArt martialArt : p.getMartialArts()) {
					if (martialArt != tournament.get().getMartialArt() || p.payedFee() == false) {
						iterator.remove();
					}
				}
			}
		} else {
			throw new TournamentNotFoundException("Tournament not found!");
		}

	}
	
}

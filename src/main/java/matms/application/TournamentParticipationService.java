package matms.application;

import java.util.Iterator;

import matms.domain.MartialArt;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.Participant;

public final class TournamentParticipationService {

	private final MartialArtsTournament tournament;
	
	public TournamentParticipationService(final MartialArtsTournament tournament) {
		this.tournament = tournament;
	}
	
	// Last one in the list should be the winner in the end
	// this method should be called after last round
	public Participant getWinner() {
		return tournament.getParticipants().get(0);
	}
	
	public void removeUnqualifiedParticipants() {
		Iterator<Participant> iterator = tournament.getParticipants().values().iterator();
		while (iterator.hasNext()) {
			Participant p = iterator.next();
			for (MartialArt martialArt : p.getMartialArts()) {
				if (martialArt != tournament.getMartialArt() || p.payedFee() == false) {
					iterator.remove();
				}
			}
		}
	}
	
}

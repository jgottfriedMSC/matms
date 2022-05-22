package matms.application;

import matms.domain.MartialArt;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.Participant;

public final class TournamentParticipationService {

	private final MartialArtsTournament tournament;
	
	public TournamentParticipationService(final MartialArtsTournament tournament) {
		this.tournament = tournament;
	}
	
	public void updateParticipantsAfterRound() {
		for (Participant participant : tournament.getParticipants()) {
			if (participant.isLoser()) {
				tournament.getParticipants().remove(participant);
			}
		}
	}
	
	// Last one in the list should be the winner in the end
	// this method should be called after last round
	public Participant getWinner() {
		return tournament.getParticipants().get(0);
	}
	
	public void removeUnqualifiedParticipants() {
		for (int i = 0; i < tournament.getParticipants().size(); i++) {
			for (MartialArt martialArt : tournament.getParticipants().get(i).getMartialArts()) {
				if (martialArt != tournament.getMartialArt()) {
					tournament.getParticipants().remove(tournament.getParticipants().get(i));
				}
			}
		}
	}
	
}

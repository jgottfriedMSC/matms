package matms.application;

import matms.abstraction.Utils;
import matms.domain.MartialArt;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.Participant;

public final class TournamentParticipationService {

	private final MartialArtsTournament tournament;
	
	public TournamentParticipationService(final MartialArtsTournament tournament) {
		this.tournament = tournament;
	}
	
	public int calculateNumberOfRounds() {
		int numberOfRounds = 0;
		
		switch(tournament.getTournamentMode()) {
		case SINGLE_KNOCKOUT:
			numberOfRounds = tournament.getParticipants().size() - 1; // n - 1
			break;
		case DOUBLE_KNOCKOUT:
			numberOfRounds = tournament.getParticipants().size() - 1; // n - 1
			break;
		case ROUND_ROBIN_TOURNAMENT:
			numberOfRounds = (Utils.factCalculator(tournament.getParticipants().size())) / (Utils.factCalculator(2)) * Utils.factCalculator(tournament.getParticipants().size() - 2); // Binomialkoeffizient
			break;
		case SWISS_SYSTEM:
			numberOfRounds = (int) ((tournament.getParticipants().size() / 2) * Utils.customLog(2, tournament.getParticipants().size())); // n/2 * log_2(n)
			break;
			
		}
		
		return numberOfRounds;
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
		for (Participant participant : tournament.getParticipants()) {
			for (MartialArt martialArt : participant.getMartialArts()) {
				if (martialArt != tournament.getMartialArt()) {
					tournament.getParticipants().remove(participant);
				}
			}
		}
	}
	
}

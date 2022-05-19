package matms.application;

import java.util.UUID;

import matms.domain.Permission;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.User;
import matms.domain.exceptions.AuthenticationException;
import matms.domain.valueobjects.Adress;
import matms.domain.valueobjects.Id;
import matms.domain.valueobjects.Money;

public final class TournamentCreationService {

	private final User user;
	private final TournamentParticipationService tournamentParticipationService;
	
	public TournamentCreationService(final User user, final TournamentParticipationService tournamentParticipationService) {
		this.user = user;
		this.tournamentParticipationService = tournamentParticipationService;
	}
	
	public MartialArtsTournament createTournament() throws AuthenticationException {
		if (user.getPermission() == Permission.TRAINER || user.getPermission() == Permission.ORGANIZER) {
			// Create Tournament with Builder
		} else {
			throw new AuthenticationException("User not allowed to create tournament!");
		}
		return null;
	}
	
}

package matms.application;

import matms.domain.Permission;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.User;
import matms.domain.exceptions.AuthenticationException;

public final class TournamentCreationService {

	private final User user;
	
	public TournamentCreationService(final User user) {
		this.user = user;
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

package matms.application;

import java.util.Map;
import java.util.UUID;

import matms.domain.MartialArt;
import matms.domain.Permission;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.Participant;
import matms.domain.entities.User;
import matms.domain.exceptions.AuthenticationException;
import matms.domain.valueobjects.Adress;
import matms.domain.valueobjects.Id;

public final class TournamentCreationService {

	private final User user;
	
	public TournamentCreationService(final User user) {
		this.user = user;
	}
	
	public MartialArtsTournament createTournament(MartialArt martialArt, Map<String, Participant> participants) throws AuthenticationException {
		MartialArtsTournament tournament;
		if (user.getPermission() == Permission.TRAINER || user.getPermission() == Permission.ORGANIZER) {
			tournament = MartialArtsTournament.builder().id(new Id(UUID.randomUUID().toString()))
					.name("BoxTournament").venue(new Adress("Germany", "Benzstra�e", 76137, 50)).participants(participants)
					.martialArt(martialArt).build();
		} else {
			throw new AuthenticationException("User not allowed to create tournament!");
		}
		return tournament;
	}
	
}

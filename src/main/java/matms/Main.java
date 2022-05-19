package matms;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import matms.application.TournamentCreationService;
import matms.application.TournamentParticipationService;
import matms.domain.*;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.Participant;
import matms.domain.entities.User;
import matms.domain.exceptions.AuthenticationException;
import matms.domain.exceptions.InvalidWeightException;
import matms.domain.valueobjects.Adress;
import matms.domain.valueobjects.Id;
import matms.domain.valueobjects.Weight;

public class Main {

	public static void main(String[] args) throws InvalidWeightException, AuthenticationException {
		
		List<Participant> partic = new ArrayList<>();
		List<MartialArt> arts = new ArrayList<>();
		arts.add(MartialArt.BOX);
		for (int i = 0; i < 10; i++) {
			Participant p = Participant.builder()
						.id(new Id(UUID.randomUUID().toString()))
						.lastName("Wurst" + i)
						.firstName("Hans" + i)
						.adress(new Adress("Germany", "Wurststraße", 76187, i))
						.weight(new Weight(80, "Kg"))
						.martialArts(arts)
						.build();
			partic.add(p);
		}
		
		var newUser = User.builder().id(new Id(UUID.randomUUID().toString()))
									.username("Test")
									.password("test1")
									.lastName("Gott")
									.firstName("Jotta")
									.permission(Permission.TRAINER)
									.build();
		
		//TournamentCreationService tournamentCreationService = new TournamentCreationService(newUser);
		//MartialArtsTournament tournament = tournamentCreationService.createTournament();
		
		//System.out.println(tournament.getMartialArt());
	}
	
}

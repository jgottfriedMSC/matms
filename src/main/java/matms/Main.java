package matms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import matms.application.KnockoutMode;
import matms.application.RoundRobinMode;
import matms.application.RoundService;
import matms.application.SwissSystemMode;
import matms.application.TournamentCreationService;
import matms.application.TournamentMode;
import matms.application.TournamentParticipationService;
import matms.domain.MartialArt;
import matms.domain.Permission;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.Participant;
import matms.domain.entities.User;
import matms.domain.exceptions.AuthenticationException;
import matms.domain.exceptions.InvalidWeightException;
import matms.domain.exceptions.NoRoundsException;
import matms.domain.exceptions.NoWinnerException;
import matms.domain.exceptions.ParticipantNotFoundException;
import matms.domain.valueobjects.Adress;
import matms.domain.valueobjects.Id;
import matms.domain.valueobjects.Weight;

public class Main {

	public static void main(String[] args)
			throws InvalidWeightException, AuthenticationException, ParticipantNotFoundException, NoRoundsException, NoWinnerException {

		Map<String, Participant> partic = new HashMap<>();
		List<MartialArt> arts = new ArrayList<>();
		List<MartialArt> otherArts = new ArrayList<>();
		arts.add(MartialArt.BOX);
		otherArts.add(MartialArt.AIKIDO);
		for (int i = 0; i < 8; i++) {
			Participant p = Participant.builder().id(new Id(UUID.randomUUID().toString())).lastName("Wurst" + i)
					.firstName("Hans" + i).adress(new Adress("Germany", "Wurststraße", 76187, i))
					.weight(new Weight(80, "Kg")).payedFee(true).martialArts(arts).build();
			Participant p_not = Participant.builder().id(new Id(UUID.randomUUID().toString())).lastName("Wurst" + i)
					.firstName("Hans" + i).adress(new Adress("Germany", "Wurststraße", 76187, i))
					.weight(new Weight(80, "Kg")).martialArts(otherArts).build();
			partic.put(p.getId().getUuid(), p);
			partic.put(p_not.getId().getUuid(), p_not);
		}

		var newUser = User.builder().id(new Id(UUID.randomUUID().toString())).username("Test").password("test1")
				.lastName("Gott").firstName("Jotta").permission(Permission.TRAINER).build();

		TournamentCreationService creationService = new TournamentCreationService(newUser);
		
		MartialArtsTournament tournament = creationService.createTournament(MartialArt.BOX, partic);

		TournamentParticipationService participationService = new TournamentParticipationService(tournament);
		participationService.removeUnqualifiedParticipants();

		TournamentMode swissMode = new SwissSystemMode(tournament);

		RoundService roundService = new RoundService(swissMode);
		
		while (roundService.checkIfThereIsWinner()) {
			roundService.playRound(roundService.nextRound());
		}
		Participant winner = roundService.getTournamentWinner();
		System.out.println("Tournament Winner: " + winner.getFirstName() + " " + winner.getLastName());
	}

}

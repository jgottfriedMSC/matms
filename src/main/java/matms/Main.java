package matms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import matms.application.RoundService;
import matms.application.TournamentParticipationService;
import matms.domain.MartialArt;
import matms.domain.Permission;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.KnockoutMode;
import matms.domain.entities.Participant;
import matms.domain.entities.SwissSystemMode;
import matms.domain.entities.User;
import matms.domain.exceptions.AuthenticationException;
import matms.domain.exceptions.InvalidWeightException;
import matms.domain.exceptions.NoRoundsException;
import matms.domain.exceptions.ParticipantNotFoundException;
import matms.domain.valueobjects.Adress;
import matms.domain.valueobjects.Id;
import matms.domain.valueobjects.Round;
import matms.domain.valueobjects.Weight;

public class Main {

	public static void main(String[] args)
			throws InvalidWeightException, AuthenticationException, ParticipantNotFoundException, NoRoundsException {

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

		MartialArtsTournament tournament = MartialArtsTournament.builder().id(new Id(UUID.randomUUID().toString()))
				.name("BoxTournament").venue(new Adress("Germany", "Benzstra�e", 76137, 50)).participants(partic)
				.martialArt(MartialArt.BOX).build();

		TournamentParticipationService participationService = new TournamentParticipationService(tournament);
		participationService.removeUnqualifiedParticipants();

		SwissSystemMode swiss = new SwissSystemMode(tournament);

		Iterator<String> iterator = partic.keySet().iterator();
		String[] keySet = new String[8];
		int i = 0;
		while (iterator.hasNext()) {
			keySet[i]= iterator.next();
			i++;
		}
		
		RoundService roundService = new RoundService(tournament, swiss);
		List<Round> rounds = roundService.initializeEncounters();
	
		Participant roundWinner1 = roundService.determineRoundWinner(rounds.get(roundService.getCurrentRound()), tournament.getParticipants().get(keySet[0]));
		Participant nextWinner1 = roundService.determineRoundWinner(rounds.get(roundService.getCurrentRound()), tournament.getParticipants().get(keySet[2]));
		roundService.createRound(roundWinner1, nextWinner1);
		
		Participant roundWinner2 = roundService.determineRoundWinner(rounds.get(roundService.getCurrentRound()), tournament.getParticipants().get(keySet[4]));
		Participant nextWinner2 = roundService.determineRoundWinner(rounds.get(roundService.getCurrentRound()), tournament.getParticipants().get(keySet[6]));
		roundService.createRound(roundWinner2, nextWinner2);
				
		System.out.println(roundService.getRounds().size());
		System.out.println(roundService.getNumberOfRounds());
		// TournamentCreationService tournamentCreationService = new
		// TournamentCreationService(newUser);
		// MartialArtsTournament tournament =
		// tournamentCreationService.createTournament();

		// System.out.println(tournament.getMartialArt());
	}

}

package matms;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import matms.adapters.InMemoryMartialArtsTournamentRepository;
import matms.adapters.InMemoryParticipantRepository;
import matms.adapters.InMemoryUserRepository;
import matms.adapters.RoundService;
import matms.application.CreateUser;
import matms.application.ParticipantService;
import matms.application.SwissSystemMode;
import matms.application.TournamentParticipationService;
import matms.application.TournamentService;
import matms.domain.MartialArt;
import matms.domain.Permission;
import matms.domain.TournamentMode;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.Participant;
import matms.domain.entities.User;
import matms.domain.exceptions.AuthenticationException;
import matms.domain.exceptions.InvalidAmountException;
import matms.domain.exceptions.InvalidWeightException;
import matms.domain.exceptions.NoRoundsException;
import matms.domain.exceptions.NoWinnerException;
import matms.domain.exceptions.ParticipantNotFoundException;
import matms.domain.exceptions.TournamentAlreadyExistsException;
import matms.domain.exceptions.TournamentNotFoundException;
import matms.domain.exceptions.UserAlreadyExistsException;
import matms.domain.repositories.MartialArtsTournamentRepository;
import matms.domain.repositories.ParticipantRepository;
import matms.domain.repositories.UserRepository;
import matms.domain.valueobjects.Adress;
import matms.domain.valueobjects.Id;
import matms.domain.valueobjects.Money;
import matms.domain.valueobjects.Weight;
import matms.plugins.TextStatistics;

public class Main {

	public static void main(String[] args)
			throws InvalidWeightException, AuthenticationException, ParticipantNotFoundException, NoRoundsException, NoWinnerException, FileNotFoundException, UserAlreadyExistsException, TournamentAlreadyExistsException, InvalidAmountException, TournamentNotFoundException {
		
		UserRepository userRepo = new InMemoryUserRepository();
		ParticipantRepository participantRepo = new InMemoryParticipantRepository();
		MartialArtsTournamentRepository martialArtsTournamentrepo = new InMemoryMartialArtsTournamentRepository();
		
		CreateUser createUser = new CreateUser(userRepo);
		ParticipantService participantService = new ParticipantService(participantRepo);
		
		
		var newUser = User.builder().id(new Id(UUID.randomUUID().toString())).username("Test").password("test1")
				.lastName("Gottfried").firstName("Justin").permission(Permission.TRAINER).build();
		
		var newUser2 = User.builder().id(new Id(UUID.randomUUID().toString())).username("Test").password("test1")
				.lastName("Gottfried").firstName("James").permission(Permission.PARTICIPANT).build();
		
		createUser.create(newUser);
		createUser.create(newUser2);
		
		TournamentService tournamentService = new TournamentService(newUser2, martialArtsTournamentrepo);
		
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
		
		var tournament = MartialArtsTournament.builder()
				.id(new Id(UUID.randomUUID().toString()))
				.name("FirstBoxTournament")
				.venue(new Adress("Germany", "Stuttgarter Straße", 66178, 15))
				.entryFee(new Money(10, "EUR"))
				.ticketPrice(new Money(15, "EUR"))
				.participants(partic)
				.martialArt(MartialArt.BOX)
				.build();
		
		tournamentService.addTournament(newUser, tournament);
		
		TournamentParticipationService participationService = new TournamentParticipationService(tournamentService.getById(tournament.getId().getUuid()));
		participationService.removeUnqualifiedParticipants();

		TournamentMode swissMode = new SwissSystemMode(tournamentService.getById(tournament.getId().getUuid()));
		RoundService roundService = new RoundService(swissMode);
		
		while (roundService.checkIfThereIsWinner()) {
			roundService.playRound(roundService.nextRound());
		}
		
		TextStatistics textStats = new TextStatistics(roundService, userRepo, martialArtsTournamentrepo);
		textStats.createDataOutputFile();
	}

}

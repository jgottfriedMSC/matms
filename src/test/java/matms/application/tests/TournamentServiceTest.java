package matms.application.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import matms.adapters.InMemoryMartialArtsTournamentRepository;
import matms.application.TournamentService;
import matms.domain.MartialArt;
import matms.domain.Permission;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.Participant;
import matms.domain.entities.User;
import matms.domain.exceptions.AuthenticationException;
import matms.domain.exceptions.InvalidAmountException;
import matms.domain.exceptions.InvalidWeightException;
import matms.domain.exceptions.ParticipantAlreadyExistsException;
import matms.domain.exceptions.TournamentAlreadyExistsException;
import matms.domain.exceptions.TournamentNotFoundException;
import matms.domain.repositories.MartialArtsTournamentRepository;
import matms.domain.valueobjects.Adress;
import matms.domain.valueobjects.Id;
import matms.domain.valueobjects.Money;
import matms.domain.valueobjects.Weight;

public class TournamentServiceTest {

	private MartialArtsTournamentRepository tournamentRepo = new InMemoryMartialArtsTournamentRepository();

	@Test
	public void constructorTest() {
		User user = initializeUser();
		TournamentService tournamentService = new TournamentService(user, tournamentRepo);
		assertFalse(tournamentService.equals(null));
	}
	
	private User initializeUser() {
		Id id = new Id(UUID.randomUUID().toString());
		User user = User.builder()
				.id(id)
				.username("test")
				.password("password")
				.firstName("a")
				.lastName("b")
				.permission(Permission.ORGANIZER)
				.build();
		return user;
	}

	@Test
	public void getByIdTest() throws ParticipantAlreadyExistsException, InvalidWeightException, AuthenticationException, TournamentAlreadyExistsException, InvalidAmountException {
		Id id = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p1 = createTestParticipant();
		Participant p2 = createTestParticipant();
		participants.put(p1.getId().getUuid(), p1);
		participants.put(p2.getId().getUuid(), p2);
		
		MartialArtsTournament tournament = MartialArtsTournament.builder()
												.id(id)
												.name("Tournament")
												.venue(venue)
												.entryFee(entryFee)
												.ticketPrice(ticketPrice)
												.participants(participants)
												.martialArt(MartialArt.BOX)
												.build();

		User user = initializeUser();
		TournamentService tournamentService = new TournamentService(user, tournamentRepo);
		
		tournamentService.addTournament(user, tournament);

		Optional<MartialArtsTournament> foundTournament = tournamentService.getById(tournament.getId().getUuid());

		assertTrue(foundTournament.get().getId().equals(tournament.getId()));
	}
	
	@Test
	public void getByNameTest() throws ParticipantAlreadyExistsException, InvalidWeightException, AuthenticationException, TournamentAlreadyExistsException, InvalidAmountException {
		Id id = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p1 = createTestParticipant();
		Participant p2 = createTestParticipant();
		participants.put(p1.getId().getUuid(), p1);
		participants.put(p2.getId().getUuid(), p2);
		
		MartialArtsTournament tournament = MartialArtsTournament.builder()
												.id(id)
												.name("Tournament")
												.venue(venue)
												.entryFee(entryFee)
												.ticketPrice(ticketPrice)
												.participants(participants)
												.martialArt(MartialArt.BOX)
												.build();

		User user = initializeUser();
		TournamentService tournamentService = new TournamentService(user, tournamentRepo);
		
		tournamentService.addTournament(user, tournament);

		Optional<MartialArtsTournament> foundTournament = tournamentService.getByName(tournament.getName());

		assertTrue(foundTournament.get().getName().equals(tournament.getName()));
	}
	
	private Participant createTestParticipant() throws InvalidWeightException {
		Id id = new Id(UUID.randomUUID().toString());
		Adress adress = new Adress("a", "b", 1, 1);
		Weight weight = new Weight(1, "KG");
		List<MartialArt> arts = new ArrayList<>();
		arts.add(MartialArt.BOX);
		Participant p = Participant.builder()
				.id(id)
				.firstName("a")
				.lastName("b")
				.adress(adress)
				.weight(weight)
				.isLoser(false)
				.payedFee(false)
				.martialArts(arts)
				.build();
		return p;
	}
	
	@Test
	public void getAllTournamentsTest() throws ParticipantAlreadyExistsException, InvalidWeightException, AuthenticationException, TournamentAlreadyExistsException, InvalidAmountException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Id id2 = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p1 = createTestParticipant();
		Participant p2 = createTestParticipant();
		participants.put(p1.getId().getUuid(), p1);
		participants.put(p2.getId().getUuid(), p2);
		
		MartialArtsTournament tournament1 = MartialArtsTournament.builder()
												.id(id1)
												.name("Tournament")
												.venue(venue)
												.entryFee(entryFee)
												.ticketPrice(ticketPrice)
												.participants(participants)
												.martialArt(MartialArt.BOX)
												.build();
		MartialArtsTournament tournament2 = MartialArtsTournament.builder()
				.id(id2)
				.name("Tournament")
				.venue(venue)
				.entryFee(entryFee)
				.ticketPrice(ticketPrice)
				.participants(participants)
				.martialArt(MartialArt.BOX)
				.build();

		User user = initializeUser();
		TournamentService tournamentService = new TournamentService(user, tournamentRepo);
		
		tournamentService.addTournament(user, tournament1);
		tournamentService.addTournament(user, tournament2);

		List<MartialArtsTournament> foundTournaments = tournamentService.getAllTournaments();

		assertTrue(foundTournaments.size() == 2);
	}
	
	@Test
	public void findAllByMartialArtTest() throws ParticipantAlreadyExistsException, InvalidWeightException, AuthenticationException, TournamentAlreadyExistsException, InvalidAmountException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Id id2 = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p1 = createTestParticipant();
		Participant p2 = createTestParticipant();
		participants.put(p1.getId().getUuid(), p1);
		participants.put(p2.getId().getUuid(), p2);
		
		MartialArtsTournament tournament1 = MartialArtsTournament.builder()
												.id(id1)
												.name("Tournament")
												.venue(venue)
												.entryFee(entryFee)
												.ticketPrice(ticketPrice)
												.participants(participants)
												.martialArt(MartialArt.BOX)
												.build();
		MartialArtsTournament tournament2 = MartialArtsTournament.builder()
				.id(id2)
				.name("Tournament")
				.venue(venue)
				.entryFee(entryFee)
				.ticketPrice(ticketPrice)
				.participants(participants)
				.martialArt(MartialArt.BOX)
				.build();

		User user = initializeUser();
		TournamentService tournamentService = new TournamentService(user, tournamentRepo);
		
		tournamentService.addTournament(user, tournament1);
		tournamentService.addTournament(user, tournament2);

		List<MartialArtsTournament> foundTournaments = tournamentService.findAllByMartialArt(MartialArt.BOX);

		assertTrue(foundTournaments.size() == 2);
	}
	
	@Test
	public void addTournamentAuthExceptionTest() throws ParticipantAlreadyExistsException, InvalidWeightException, AuthenticationException, TournamentAlreadyExistsException, InvalidAmountException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p1 = createTestParticipant();
		Participant p2 = createTestParticipant();
		participants.put(p1.getId().getUuid(), p1);
		participants.put(p2.getId().getUuid(), p2);
		
		MartialArtsTournament tournament1 = MartialArtsTournament.builder()
												.id(id1)
												.name("Tournament")
												.venue(venue)
												.entryFee(entryFee)
												.ticketPrice(ticketPrice)
												.participants(participants)
												.martialArt(MartialArt.BOX)
												.build();

		Id id = new Id(UUID.randomUUID().toString());
		User user = User.builder()
				.id(id)
				.username("test")
				.password("password")
				.firstName("a")
				.lastName("b")
				.permission(Permission.PARTICIPANT)
				.build();
		
		TournamentService tournamentService = new TournamentService(user, tournamentRepo);

		assertThrows(AuthenticationException.class, () -> {
			tournamentService.addTournament(user, tournament1);
		});
	}
	
	@Test
	public void addTournamentTournamentAlreadyExistsExceptionTest() throws ParticipantAlreadyExistsException, InvalidWeightException, AuthenticationException, TournamentAlreadyExistsException, InvalidAmountException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p1 = createTestParticipant();
		Participant p2 = createTestParticipant();
		participants.put(p1.getId().getUuid(), p1);
		participants.put(p2.getId().getUuid(), p2);
		
		MartialArtsTournament tournament1 = MartialArtsTournament.builder()
												.id(id1)
												.name("Tournament")
												.venue(venue)
												.entryFee(entryFee)
												.ticketPrice(ticketPrice)
												.participants(participants)
												.martialArt(MartialArt.BOX)
												.build();
		MartialArtsTournament tournament2 = MartialArtsTournament.builder()
				.id(id1)
				.name("Tournament")
				.venue(venue)
				.entryFee(entryFee)
				.ticketPrice(ticketPrice)
				.participants(participants)
				.martialArt(MartialArt.BOX)
				.build();

		User user = initializeUser();
		TournamentService tournamentService = new TournamentService(user, tournamentRepo);

		tournamentService.addTournament(user, tournament1);
		
		assertThrows(TournamentAlreadyExistsException.class, () -> {
			tournamentService.addTournament(user, tournament2);
		});
	}
	
	@Test
	public void removeTournamentAuthExceptionTest() throws ParticipantAlreadyExistsException, InvalidWeightException, AuthenticationException, TournamentAlreadyExistsException, InvalidAmountException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p1 = createTestParticipant();
		Participant p2 = createTestParticipant();
		participants.put(p1.getId().getUuid(), p1);
		participants.put(p2.getId().getUuid(), p2);
		
		MartialArtsTournament tournament1 = MartialArtsTournament.builder()
												.id(id1)
												.name("Tournament")
												.venue(venue)
												.entryFee(entryFee)
												.ticketPrice(ticketPrice)
												.participants(participants)
												.martialArt(MartialArt.BOX)
												.build();

		Id id = new Id(UUID.randomUUID().toString());
		User user = User.builder()
				.id(id)
				.username("test")
				.password("password")
				.firstName("a")
				.lastName("b")
				.permission(Permission.PARTICIPANT)
				.build();
		
		TournamentService tournamentService = new TournamentService(user, tournamentRepo);

		assertThrows(AuthenticationException.class, () -> {
			tournamentService.removeTournament(tournament1);
		});
	}
	
	@Test
	public void removeTournamentTournamentNotFoundExceptionTest() throws ParticipantAlreadyExistsException, InvalidWeightException, AuthenticationException, TournamentAlreadyExistsException, InvalidAmountException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p1 = createTestParticipant();
		Participant p2 = createTestParticipant();
		participants.put(p1.getId().getUuid(), p1);
		participants.put(p2.getId().getUuid(), p2);
		
		MartialArtsTournament tournament1 = MartialArtsTournament.builder()
												.id(id1)
												.name("Tournament")
												.venue(venue)
												.entryFee(entryFee)
												.ticketPrice(ticketPrice)
												.participants(participants)
												.martialArt(MartialArt.BOX)
												.build();

		User user = initializeUser();
		TournamentService tournamentService = new TournamentService(user, tournamentRepo);

		assertThrows(TournamentNotFoundException.class, () -> {
			tournamentService.removeTournament(tournament1);
		});
	}
	
	@Test
	public void removeTournamentTest() throws ParticipantAlreadyExistsException, InvalidWeightException, AuthenticationException, InvalidAmountException, TournamentNotFoundException, TournamentAlreadyExistsException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Id id2 = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p1 = createTestParticipant();
		Participant p2 = createTestParticipant();
		participants.put(p1.getId().getUuid(), p1);
		participants.put(p2.getId().getUuid(), p2);
		
		MartialArtsTournament tournament1 = MartialArtsTournament.builder()
												.id(id1)
												.name("Tournament")
												.venue(venue)
												.entryFee(entryFee)
												.ticketPrice(ticketPrice)
												.participants(participants)
												.martialArt(MartialArt.BOX)
												.build();
		MartialArtsTournament tournament2 = MartialArtsTournament.builder()
				.id(id2)
				.name("Tournament")
				.venue(venue)
				.entryFee(entryFee)
				.ticketPrice(ticketPrice)
				.participants(participants)
				.martialArt(MartialArt.BOX)
				.build();
		
		User user = initializeUser();
		TournamentService tournamentService = new TournamentService(user, tournamentRepo);

		tournamentService.addTournament(user, tournament1);
		tournamentService.addTournament(user, tournament2);
		
		tournamentService.removeTournament(tournament1);
		
		assertTrue(tournamentService.getAllTournaments().size() == 1);
	}
	
	@Test
	public void udpateTournamentAuthExceptionTest() throws ParticipantAlreadyExistsException, InvalidWeightException, AuthenticationException, InvalidAmountException, TournamentNotFoundException, TournamentAlreadyExistsException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p1 = createTestParticipant();
		Participant p2 = createTestParticipant();
		participants.put(p1.getId().getUuid(), p1);
		participants.put(p2.getId().getUuid(), p2);
		
		MartialArtsTournament tournament1 = MartialArtsTournament.builder()
												.id(id1)
												.name("Tournament")
												.venue(venue)
												.entryFee(entryFee)
												.ticketPrice(ticketPrice)
												.participants(participants)
												.martialArt(MartialArt.BOX)
												.build();

		Id id = new Id(UUID.randomUUID().toString());
		User user = User.builder()
				.id(id)
				.username("test")
				.password("password")
				.firstName("a")
				.lastName("b")
				.permission(Permission.PARTICIPANT)
				.build();
		
		TournamentService tournamentService = new TournamentService(user, tournamentRepo);

		assertThrows(AuthenticationException.class, () -> {
			tournamentService.updateTournament(tournament1);
		});
	}
	
	@Test
	public void updateTournamentTournamentNotFoundExceptionTest() throws InvalidAmountException, InvalidWeightException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p1 = createTestParticipant();
		Participant p2 = createTestParticipant();
		participants.put(p1.getId().getUuid(), p1);
		participants.put(p2.getId().getUuid(), p2);
		
		MartialArtsTournament tournament1 = MartialArtsTournament.builder()
												.id(id1)
												.name("Tournament")
												.venue(venue)
												.entryFee(entryFee)
												.ticketPrice(ticketPrice)
												.participants(participants)
												.martialArt(MartialArt.BOX)
												.build();

		User user = initializeUser();
		TournamentService tournamentService = new TournamentService(user, tournamentRepo);

		assertThrows(TournamentNotFoundException.class, () -> {
			tournamentService.updateTournament(tournament1);
		});
	}
	
	@Test
	public void updateTournamentTest() throws InvalidAmountException, InvalidWeightException, AuthenticationException, TournamentAlreadyExistsException, TournamentNotFoundException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p1 = createTestParticipant();
		Participant p2 = createTestParticipant();
		participants.put(p1.getId().getUuid(), p1);
		participants.put(p2.getId().getUuid(), p2);
		
		MartialArtsTournament tournament1 = MartialArtsTournament.builder()
												.id(id1)
												.name("Tournament")
												.venue(venue)
												.entryFee(entryFee)
												.ticketPrice(ticketPrice)
												.participants(participants)
												.martialArt(MartialArt.BOX)
												.build();
		MartialArtsTournament tournament2 = MartialArtsTournament.builder()
				.id(id1)
				.name("Tournament1")
				.venue(venue)
				.entryFee(entryFee)
				.ticketPrice(ticketPrice)
				.participants(participants)
				.martialArt(MartialArt.BOX)
				.build();

		User user = initializeUser();
		TournamentService tournamentService = new TournamentService(user, tournamentRepo);

		tournamentService.addTournament(user, tournament1);
		tournamentService.updateTournament(tournament2);
		
		assertTrue(tournamentService.getById(tournament2.getId().getUuid()).get().getName().equals("Tournament1"));
	}
}

package matms.adapter.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import matms.adapters.InMemoryMartialArtsTournamentRepository;
import matms.domain.MartialArt;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.Participant;
import matms.domain.exceptions.InvalidAmountException;
import matms.domain.exceptions.InvalidWeightException;
import matms.domain.repositories.MartialArtsTournamentRepository;
import matms.domain.valueobjects.Adress;
import matms.domain.valueobjects.Id;
import matms.domain.valueobjects.Money;
import matms.domain.valueobjects.Weight;

public class InMemoryMartialArtsTournamentRepositorySpec {

	MartialArtsTournamentRepository tournamentRepo = new InMemoryMartialArtsTournamentRepository();

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
	public void getByIdTest() throws InvalidWeightException, InvalidAmountException {
		Id id = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p = createTestParticipant();
		participants.put(p.getId().getUuid(), p);
		
		MartialArtsTournament tournament = MartialArtsTournament.builder()
												.id(id)
												.name("Tournament")
												.venue(venue)
												.entryFee(entryFee)
												.ticketPrice(ticketPrice)
												.participants(participants)
												.martialArt(MartialArt.BOX)
												.build();
		
		tournamentRepo.addTournament(tournament);
		
		Optional<MartialArtsTournament> getTournament = tournamentRepo.getById(id.getUuid());
		assertTrue(tournament.equals(getTournament.get()));
	}
	
	@Test
	public void getByNameTest() throws InvalidWeightException, InvalidAmountException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p = createTestParticipant();
		participants.put(p.getId().getUuid(), p);
		
		MartialArtsTournament tournament1 = MartialArtsTournament.builder()
												.id(id1)
												.name("Tournament")
												.venue(venue)
												.entryFee(entryFee)
												.ticketPrice(ticketPrice)
												.participants(participants)
												.martialArt(MartialArt.BOX)
												.build();
		
		tournamentRepo.addTournament(tournament1);
		
		Optional<MartialArtsTournament> tournament = tournamentRepo.getByName("Tournament");
		assertTrue(tournament.get().equals(tournament1));
	}
	
	@Test
	public void getAllTournamentsTest() throws InvalidWeightException, InvalidAmountException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Id id2 = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p = createTestParticipant();
		participants.put(p.getId().getUuid(), p);
		
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
		
		tournamentRepo.addTournament(tournament1);
		tournamentRepo.addTournament(tournament2);
		
		List<MartialArtsTournament> tournaments = tournamentRepo.getAllTournaments();
		assertTrue(tournaments.size() == 2);
	}
	
	@Test
	public void findAllByMartialArtTest() throws InvalidAmountException, InvalidWeightException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Id id2 = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p = createTestParticipant();
		participants.put(p.getId().getUuid(), p);
		
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
		
		tournamentRepo.addTournament(tournament1);
		tournamentRepo.addTournament(tournament2);
		
		List<MartialArtsTournament> tournaments = tournamentRepo.findAllByMartialArt(MartialArt.BOX);
		assertTrue(tournaments.size() == 2);
	}
	
	@Test
	public void deleteParticipantTest() throws InvalidWeightException, InvalidAmountException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Id id2 = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p = createTestParticipant();
		participants.put(p.getId().getUuid(), p);
		
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
		
		tournamentRepo.addTournament(tournament1);
		tournamentRepo.addTournament(tournament2);
		
		tournamentRepo.removeTournament(tournament2);
		List<MartialArtsTournament> tournaments = tournamentRepo.getAllTournaments();
		assertTrue(tournaments.size() == 1);
	}
	
	@Test
	public void updateUserTest() throws InvalidWeightException, InvalidAmountException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p = createTestParticipant();
		participants.put(p.getId().getUuid(), p);
		
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
		
		tournamentRepo.addTournament(tournament1);
		tournamentRepo.updateTournament(tournament2);
		
		assertTrue(tournamentRepo.getById(id1.getUuid()).get().getName().equals(tournament2.getName()));
	}
	
	@Test
	public void nextIdTest() {
		Id id1 = new Id(UUID.randomUUID().toString());
		Id id2 = tournamentRepo.nextId();
		
		assertFalse(id1.equals(id2));
	}
	
}

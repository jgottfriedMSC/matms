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

import matms.application.RoundRobinMode;
import matms.domain.MartialArt;
import matms.domain.Round;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.Participant;
import matms.domain.exceptions.InvalidAmountException;
import matms.domain.exceptions.InvalidWeightException;
import matms.domain.exceptions.NoWinnerException;
import matms.domain.exceptions.TournamentNotFoundException;
import matms.domain.valueobjects.Adress;
import matms.domain.valueobjects.Id;
import matms.domain.valueobjects.Match;
import matms.domain.valueobjects.Money;
import matms.domain.valueobjects.Weight;

public class RoundRobinModeTest {
	@Test
	public void constructorAndCalculateNumberOfMatchesTest() throws TournamentNotFoundException, InvalidAmountException, InvalidWeightException {
		Id id = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p1 = createTestParticipant();
		Participant p2 = createTestParticipant();
		participants.put(p1.getId().getUuid(), p1);
		participants.put(p2.getId().getUuid(), p2);
		
		Optional<MartialArtsTournament> tournament = Optional.of(MartialArtsTournament.builder()
												.id(id)
												.name("Tournament")
												.venue(venue)
												.entryFee(entryFee)
												.ticketPrice(ticketPrice)
												.participants(participants)
												.martialArt(MartialArt.BOX)
												.build());
		
		RoundRobinMode roundRobin = new RoundRobinMode(tournament);
		assertFalse(roundRobin.equals(false));
	}
	
	
	@Test
	public void calculateNumberOfMatchesTournamentNotFoundExceptionTest() throws InvalidAmountException, InvalidWeightException, TournamentNotFoundException {
		Optional<MartialArtsTournament> tournament = Optional.empty();
		
		assertThrows(TournamentNotFoundException.class, () -> {
			new RoundRobinMode(tournament);
		});
	}
	
	@Test
	public void playRoundTest() throws InvalidAmountException, InvalidWeightException, TournamentNotFoundException {
		Id id = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p1 = createTestParticipant();
		Participant p2 = createTestParticipant();
		participants.put(p1.getId().getUuid(), p1);
		participants.put(p2.getId().getUuid(), p2);
		
		Optional<MartialArtsTournament> tournament = Optional.of(MartialArtsTournament.builder()
												.id(id)
												.name("Tournament")
												.venue(venue)
												.entryFee(entryFee)
												.ticketPrice(ticketPrice)
												.participants(participants)
												.martialArt(MartialArt.BOX)
												.build());
		
		RoundRobinMode roundRobin = new RoundRobinMode(tournament);
		
		List<Match> matches =  new ArrayList<>();
		matches.add(new Match(p1, p2));
		Round r = new Round(matches);
		
		roundRobin.playRound(r);
		
		
		assertTrue(p1.isLoser());
	}
	
	@Test
	public void nextRoundEvenTest() throws InvalidAmountException, InvalidWeightException, TournamentNotFoundException {
		Id id = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p1 = createTestParticipant();
		Participant p2 = createTestParticipant();
		participants.put(p1.getId().getUuid(), p1);
		participants.put(p2.getId().getUuid(), p2);
		
		Optional<MartialArtsTournament> tournament = Optional.of(MartialArtsTournament.builder()
												.id(id)
												.name("Tournament")
												.venue(venue)
												.entryFee(entryFee)
												.ticketPrice(ticketPrice)
												.participants(participants)
												.martialArt(MartialArt.BOX)
												.build());
		
		RoundRobinMode roundRobin = new RoundRobinMode(tournament);
		
		Round r = roundRobin.nextRound();
		
		List<Match> m = r.getMatches();
		
		assertTrue(p1.equals(m.get(0).getOpponent()) || p1.equals(m.get(0).getParticipant()));
		assertTrue(p2.equals(m.get(0).getOpponent()) || p2.equals(m.get(0).getParticipant()));
	}
	
	@Test
	public void getWinnerTest() throws InvalidAmountException, InvalidWeightException, TournamentNotFoundException, NoWinnerException {
		Id id = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p1 = createTestParticipant();
		Participant p2 = createTestParticipant();
		participants.put(p1.getId().getUuid(), p1);
		participants.put(p2.getId().getUuid(), p2);
		
		Optional<MartialArtsTournament> tournament = Optional.of(MartialArtsTournament.builder()
												.id(id)
												.name("Tournament")
												.venue(venue)
												.entryFee(entryFee)
												.ticketPrice(ticketPrice)
												.participants(participants)
												.martialArt(MartialArt.BOX)
												.build());
		
		RoundRobinMode roundRobin = new RoundRobinMode(tournament);
		
		Round r = roundRobin.nextRound();
		roundRobin.playRound(r);
		
		assertTrue(p1.equals(roundRobin.getWinner()) || p2.equals(roundRobin.getWinner()));
	}
	
	@Test
	public void checkIfThereIsWinnerTest() throws InvalidAmountException, InvalidWeightException, TournamentNotFoundException, NoWinnerException {
		Id id = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p1 = createTestParticipant();
		Participant p2 = createTestParticipant();
		participants.put(p1.getId().getUuid(), p1);
		participants.put(p2.getId().getUuid(), p2);
		
		Optional<MartialArtsTournament> tournament = Optional.of(MartialArtsTournament.builder()
												.id(id)
												.name("Tournament")
												.venue(venue)
												.entryFee(entryFee)
												.ticketPrice(ticketPrice)
												.participants(participants)
												.martialArt(MartialArt.BOX)
												.build());
		
		RoundRobinMode roundRobin = new RoundRobinMode(tournament);
		
		Round r = roundRobin.nextRound();
		roundRobin.playRound(r);
		
		assertTrue(!roundRobin.checkIfThereIsWinner() == true);
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
	
}

package matms.application.tests;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import matms.application.KnockoutMode;
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

public class KnockoutModeSpec {
	
	@Test
	public void constructorTest() {
		Optional<MartialArtsTournament> tournament = Mockito.mock(Optional.class);
		KnockoutMode knockout = new KnockoutMode(tournament);
		assertFalse(knockout.equals(false));
	}
	
	@Test
	public void calculateNumberOfMatchesTest() throws InvalidAmountException, InvalidWeightException, TournamentNotFoundException {
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
		
		KnockoutMode knockout = new KnockoutMode(tournament);
		
		BigInteger numberOfMatches = knockout.calculateNumberOfMatches();
		
		assertTrue(numberOfMatches.intValue() == 1);
	}
	
	@Test
	public void calculateNumberOfMatchesTournamentNotFoundExceptionTest() throws InvalidAmountException, InvalidWeightException, TournamentNotFoundException {
		Optional<MartialArtsTournament> tournament = Optional.empty();
		
		KnockoutMode knockout = new KnockoutMode(tournament);
		
		assertThrows(TournamentNotFoundException.class, () -> {
			knockout.calculateNumberOfMatches();
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
		
		KnockoutMode knockout = new KnockoutMode(tournament);
		
		List<Match> matches =  new ArrayList<>();
		matches.add(new Match(p1, p2));
		Round r = new Round(matches);
		
		knockout.playRound(r);
		
		assertTrue(p1.isLoser());
	}
	
	@Test
	public void playRoundTournamentNotFoundExceptionTest() throws InvalidAmountException, InvalidWeightException, TournamentNotFoundException {
		Optional<MartialArtsTournament> tournament = Optional.empty();
		
		KnockoutMode knockout = new KnockoutMode(tournament);
		
		List<Match> matches =  new ArrayList<>();
		matches.add(new Match(null, null));
		Round r = new Round(matches);
		
		assertThrows(TournamentNotFoundException.class, () -> {
			knockout.playRound(r);
		});
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
		
		KnockoutMode knockout = new KnockoutMode(tournament);
		
		Round r = knockout.nextRound();
		
		List<Match> m = r.getMatches();
		
		assertTrue(p1.equals(m.get(0).getOpponent()) || p1.equals(m.get(0).getParticipant()));
		assertTrue(p2.equals(m.get(0).getOpponent()) || p2.equals(m.get(0).getParticipant()));
	}
	
	@Test
	public void nextRoundOddTest() throws InvalidAmountException, InvalidWeightException, TournamentNotFoundException {
		Id id = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p1 = createTestParticipant();
		Participant p2 = createTestParticipant();
		Participant p3 = createTestParticipant();
		participants.put(p1.getId().getUuid(), p1);
		participants.put(p2.getId().getUuid(), p2);
		participants.put(p3.getId().getUuid(), p3);
		
		Optional<MartialArtsTournament> tournament = Optional.of(MartialArtsTournament.builder()
												.id(id)
												.name("Tournament")
												.venue(venue)
												.entryFee(entryFee)
												.ticketPrice(ticketPrice)
												.participants(participants)
												.martialArt(MartialArt.BOX)
												.build());
		
		KnockoutMode knockout = new KnockoutMode(tournament);
		
		Round r = knockout.nextRound();
		
		List<Match> m = r.getMatches();
		
		assertTrue(p1.equals(m.get(0).getParticipant()) || p2.equals(m.get(0).getParticipant()) || p3.equals(m.get(0).getParticipant()));
		assertTrue(p1.equals(m.get(0).getOpponent()) || p2.equals(m.get(0).getOpponent()) || p3.equals(m.get(0).getOpponent()));
	}
	
	@Test
	public void nextRoundTournamentNotFoundExceptionTest() throws InvalidAmountException, InvalidWeightException, TournamentNotFoundException {
		Optional<MartialArtsTournament> tournament = Optional.empty();
		
		KnockoutMode knockout = new KnockoutMode(tournament);
		
		assertThrows(TournamentNotFoundException.class, () -> {
			knockout.nextRound();
		});
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
		
		KnockoutMode knockout = new KnockoutMode(tournament);
		
		Round r = knockout.nextRound();
		knockout.playRound(r);
		
		assertTrue(p1.equals(knockout.getWinner()) || p2.equals(knockout.getWinner()));
	}
	
	@Test
	public void getWinnerMoreThanTwoTest() throws InvalidAmountException, InvalidWeightException, TournamentNotFoundException, NoWinnerException {
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
		
		KnockoutMode knockout = new KnockoutMode(tournament);
		
		Round r = knockout.nextRound();
		knockout.playRound(r);
		
		Participant p3 = createTestParticipant();
		
		tournament.get().getParticipants().put(p3.getId().getUuid(), p3);
		
		assertThrows(NoWinnerException.class, () -> {
			knockout.getWinner();
		});
	}
	
	@Test
	public void getWinnerTournamentNotFoundExceptionTest() throws InvalidAmountException, InvalidWeightException, TournamentNotFoundException, NoWinnerException {
		Optional<MartialArtsTournament> tournament = Optional.empty();
		
		KnockoutMode knockout = new KnockoutMode(tournament);
		
		assertThrows(TournamentNotFoundException.class, () -> {
			knockout.getWinner();
		});
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
		
		KnockoutMode knockout = new KnockoutMode(tournament);
		
		Round r = knockout.nextRound();
		knockout.playRound(r);
		
		assertTrue(!knockout.checkIfThereIsWinner() == true);
	}
	
	@Test
	public void checkIfThereIsWinnerTournamentNotFoundExceptionTest() throws InvalidAmountException, InvalidWeightException, TournamentNotFoundException, NoWinnerException {		
		Optional<MartialArtsTournament> tournament = Optional.empty();
		
		KnockoutMode knockout = new KnockoutMode(tournament);
		
		assertThrows(TournamentNotFoundException.class, () -> {
			knockout.checkIfThereIsWinner();
		});
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

package matms.domain.aggregates.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;

import matms.domain.MartialArt;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.Participant;
import matms.domain.exceptions.InvalidAmountException;
import matms.domain.exceptions.InvalidWeightException;
import matms.domain.valueobjects.Adress;
import matms.domain.valueobjects.Id;
import matms.domain.valueobjects.Money;
import matms.domain.valueobjects.Weight;

public class MartialArtsTournamentTest {

	private static MartialArtsTournament tournament;
	private static Id id;
	private static Adress venue;
	private static Money entryFee;
	private static Money ticketPrice;
	private static Map<String, Participant> participants;
	
	@BeforeAll
	public static void initializeTournament() {
		id = Mockito.mock(Id.class);
		venue = Mockito.mock(Adress.class);
		entryFee = Mockito.mock(Money.class);
		ticketPrice = Mockito.mock(Money.class);
		participants = Mockito.mock(Map.class);
		
		tournament = MartialArtsTournament.builder()
						.id(id)
						.name("Tournament")
						.venue(venue)
						.entryFee(entryFee)
						.ticketPrice(ticketPrice)
						.numberOfTicketsSold(0)
						.participants(participants)
						.martialArt(MartialArt.BOX)
						.build();
	}
	
	@Test
	public void builderTest() {
		assertTrue(tournament.getId().equals(id));
		assertTrue(tournament.getName().equals("Tournament"));
		assertTrue(tournament.getVenue().equals(venue));
		assertTrue(tournament.getEntryFee().equals(entryFee));
		assertTrue(tournament.getTicketPrice().equals(ticketPrice));
		assertTrue(tournament.getNumberOfTicketsSold() == 0);
		assertTrue(tournament.getParticipants().equals(participants));
		assertTrue(tournament.getMartialArt().equals(MartialArt.BOX));
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
	public void toStringWithOneParticipantTest() throws InvalidAmountException, InvalidWeightException {
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
		String tournamentString = "MartialArtsTournament{" +
				"id='" + id.getUuid().toString() + "'" +
				", name='Tournament'" +
				", venue='" + venue.toString() + "'" +
				", entryFee='" + entryFee + "'" +
				", ticketPrice='" + ticketPrice + "'" +
				", participants='" + p.getId().getUuid() + "'" + 
				", martialArt='" + MartialArt.BOX.toString() + "'}";
		assertTrue(tournament.toString().equals(tournamentString));
		
	}
	
	@Test
	public void toStringWithMoreParticipantsTest() throws InvalidAmountException, InvalidWeightException {
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
		String tournamentString = "MartialArtsTournament{" +
				"id='" + id.getUuid().toString() + "'" +
				", name='Tournament'" +
				", venue='" + venue.toString() + "'" +
				", entryFee='" + entryFee + "'" +
				", ticketPrice='" + ticketPrice + "'" +
				", participants='" + p1.getId().getUuid() + "," + p2.getId().getUuid() + ",'" + 
				", martialArt='" + MartialArt.BOX.toString() + "'}";
		assertTrue(tournament.toString().equals(tournamentString));
		
	}
	
	
	
}

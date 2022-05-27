package matms.application.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import matms.application.TournamentParticipationService;
import matms.domain.MartialArt;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.Participant;
import matms.domain.exceptions.InvalidAmountException;
import matms.domain.exceptions.InvalidWeightException;
import matms.domain.exceptions.TournamentNotFoundException;
import matms.domain.valueobjects.Adress;
import matms.domain.valueobjects.Id;
import matms.domain.valueobjects.Money;
import matms.domain.valueobjects.Weight;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TournamentParticipationServiceSpec {

	private Optional<MartialArtsTournament> tournament;
	
	@Test
	public void constructorTest() {
		tournament = Mockito.mock(Optional.class);
		TournamentParticipationService service = new TournamentParticipationService(tournament);
		assertFalse(service.equals(null));
	}
	
	@Test
	public void removeUnqualifiedParticipantsTest() throws TournamentNotFoundException, InvalidWeightException, InvalidAmountException {
		Id id = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p1 = createTestParticipant(MartialArt.BOX, true);
		Participant p2 = createTestParticipant(MartialArt.KARATE, true);
		participants.put(p1.getId().getUuid(), p1);
		participants.put(p2.getId().getUuid(), p2);
		
		MartialArtsTournament tournament1 = MartialArtsTournament.builder()
												.id(id)
												.name("Tournament")
												.venue(venue)
												.entryFee(entryFee)
												.ticketPrice(ticketPrice)
												.participants(participants)
												.martialArt(MartialArt.BOX)
												.build();
		Optional<MartialArtsTournament> optionalTournament = Optional.of(tournament1);
		
		TournamentParticipationService service = new TournamentParticipationService(optionalTournament);
		service.removeUnqualifiedParticipants();
		assertTrue(optionalTournament.get().getParticipants().size() == 1);
		
	}
	
	@Test
	public void removeUnqualifiedParticipantsFeeTest() throws TournamentNotFoundException, InvalidWeightException, InvalidAmountException {
		Id id = new Id(UUID.randomUUID().toString());
		Adress venue = new Adress("a", "b", 1, 1);
		Money entryFee = new Money(1, "EUR");
		Money ticketPrice = new Money(1, "EUR");
		Map<String, Participant> participants = new HashMap<>();
		Participant p1 = createTestParticipant(MartialArt.BOX, false);
		Participant p2 = createTestParticipant(MartialArt.BOX, false);

		participants.put(p1.getId().getUuid(), p1);
		participants.put(p2.getId().getUuid(), p2);
		
		MartialArtsTournament tournament1 = MartialArtsTournament.builder()
												.id(id)
												.name("Tournament")
												.venue(venue)
												.entryFee(entryFee)
												.ticketPrice(ticketPrice)
												.participants(participants)
												.martialArt(MartialArt.BOX)
												.build();
		Optional<MartialArtsTournament> optionalTournament = Optional.of(tournament1);
		
		TournamentParticipationService service = new TournamentParticipationService(optionalTournament);
		service.removeUnqualifiedParticipants();
		assertTrue(optionalTournament.get().getParticipants().size() == 0);
		
	}
	
	@Test
	public void removeUnqualifiedParticipantsExceptionTest() throws TournamentNotFoundException, InvalidWeightException, InvalidAmountException {
		Optional<MartialArtsTournament> optionalTournament = Optional.empty();
		
		TournamentParticipationService service = new TournamentParticipationService(optionalTournament);	
		assertThrows(TournamentNotFoundException.class, () -> {
			service.removeUnqualifiedParticipants();
		});
		
	}
	
	private Participant createTestParticipant(MartialArt art, boolean payedFee) throws InvalidWeightException {
		Id id = new Id(UUID.randomUUID().toString());
		Adress adress = new Adress("a", "b", 1, 1);
		Weight weight = new Weight(1, "KG");
		List<MartialArt> arts = new ArrayList<>();
		arts.add(art);
		Participant p = Participant.builder()
				.id(id)
				.firstName("a")
				.lastName("b")
				.adress(adress)
				.weight(weight)
				.isLoser(false)
				.payedFee(payedFee)
				.martialArts(arts)
				.build();
		return p;
	}
}

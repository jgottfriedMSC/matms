package matms.adapter.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import matms.adapters.InMemoryParticipantRepository;
import matms.domain.MartialArt;
import matms.domain.entities.Participant;
import matms.domain.exceptions.InvalidWeightException;
import matms.domain.repositories.ParticipantRepository;
import matms.domain.valueobjects.Adress;
import matms.domain.valueobjects.Id;
import matms.domain.valueobjects.Weight;

public class InMemoryParticipantRepositoryTest {

	ParticipantRepository participantRepo = new InMemoryParticipantRepository();

	@Test
	public void getByIdTest() throws InvalidWeightException {
		Id id = new Id(UUID.randomUUID().toString());
		Adress adress = new Adress("a", "b", 1, 1);
		Weight weight = new Weight(1, "KG");
		List<MartialArt> arts = new ArrayList<>();
		arts.add(MartialArt.BOX);
		arts.add(MartialArt.KARATE);
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
		
		participantRepo.addParticipant(p);
		
		Optional<Participant> getParticipant = participantRepo.getById(id.getUuid());
		assertTrue(p.equals(getParticipant.get()));
	}
	
	@Test
	public void getByMartialArtTest() throws InvalidWeightException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Id id2 = new Id(UUID.randomUUID().toString());
		Adress adress = new Adress("a", "b", 1, 1);
		Weight weight = new Weight(1, "KG");
		List<MartialArt> arts = new ArrayList<>();
		arts.add(MartialArt.BOX);
		arts.add(MartialArt.KARATE);
		Participant p1 = Participant.builder()
				.id(id1)
				.firstName("a")
				.lastName("b")
				.adress(adress)
				.weight(weight)
				.isLoser(false)
				.payedFee(false)
				.martialArts(arts)
				.build();
		Participant p2 = Participant.builder()
				.id(id2)
				.firstName("a")
				.lastName("b")
				.adress(adress)
				.weight(weight)
				.isLoser(false)
				.payedFee(false)
				.martialArts(arts)
				.build();
		
		participantRepo.addParticipant(p1);
		participantRepo.addParticipant(p2);
		
		List<Participant> participants = participantRepo.getByMartialArt(MartialArt.BOX);
		assertTrue(participants.size() == 2);
	}
	
	@Test
	public void getAllParticipantsTest() throws InvalidWeightException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Id id2 = new Id(UUID.randomUUID().toString());
		Adress adress = new Adress("a", "b", 1, 1);
		Weight weight = new Weight(1, "KG");
		List<MartialArt> arts = new ArrayList<>();
		arts.add(MartialArt.BOX);
		arts.add(MartialArt.KARATE);
		Participant p1 = Participant.builder()
				.id(id1)
				.firstName("a")
				.lastName("b")
				.adress(adress)
				.weight(weight)
				.isLoser(false)
				.payedFee(false)
				.martialArts(arts)
				.build();
		Participant p2 = Participant.builder()
				.id(id2)
				.firstName("a")
				.lastName("b")
				.adress(adress)
				.weight(weight)
				.isLoser(false)
				.payedFee(false)
				.martialArts(arts)
				.build();
		
		participantRepo.addParticipant(p1);
		participantRepo.addParticipant(p2);
		
		List<Participant> participants = participantRepo.getAllParticipants();
		assertTrue(participants.size() == 2);
	}
	
	@Test
	public void deleteParticipantTest() throws InvalidWeightException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Id id2 = new Id(UUID.randomUUID().toString());
		Adress adress = new Adress("a", "b", 1, 1);
		Weight weight = new Weight(1, "KG");
		List<MartialArt> arts = new ArrayList<>();
		arts.add(MartialArt.BOX);
		arts.add(MartialArt.KARATE);
		Participant p1 = Participant.builder()
				.id(id1)
				.firstName("a")
				.lastName("b")
				.adress(adress)
				.weight(weight)
				.isLoser(false)
				.payedFee(false)
				.martialArts(arts)
				.build();
		Participant p2 = Participant.builder()
				.id(id2)
				.firstName("a")
				.lastName("b")
				.adress(adress)
				.weight(weight)
				.isLoser(false)
				.payedFee(false)
				.martialArts(arts)
				.build();
		
		participantRepo.addParticipant(p1);
		participantRepo.addParticipant(p2);
		
		participantRepo.deleteParticipant(p2);
		List<Participant> participants = participantRepo.getAllParticipants();
		assertTrue(participants.size() == 1);
	}
	
	@Test
	public void updateUserTest() throws InvalidWeightException {
		Id id1 = new Id(UUID.randomUUID().toString());

		Adress adress = new Adress("a", "b", 1, 1);
		Weight weight = new Weight(1, "KG");
		List<MartialArt> arts = new ArrayList<>();
		arts.add(MartialArt.BOX);
		arts.add(MartialArt.KARATE);
		Participant p1 = Participant.builder()
				.id(id1)
				.firstName("a")
				.lastName("b")
				.adress(adress)
				.weight(weight)
				.isLoser(false)
				.payedFee(false)
				.martialArts(arts)
				.build();
		Participant p2 = Participant.builder()
				.id(id1)
				.firstName("a")
				.lastName("c")
				.adress(adress)
				.weight(weight)
				.isLoser(false)
				.payedFee(false)
				.martialArts(arts)
				.build();
		
		participantRepo.addParticipant(p1);
		participantRepo.updateParticipant(p2);
		assertTrue(participantRepo.getById(id1.getUuid()).get().getLastName().equals(p2.getLastName()));
	}
	
	@Test
	public void nextIdTest() {
		Id id1 = new Id(UUID.randomUUID().toString());
		Id id2 = participantRepo.nextId();
		
		assertFalse(id1.equals(id2));
	}
	
}

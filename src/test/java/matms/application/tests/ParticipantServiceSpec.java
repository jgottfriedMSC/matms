package matms.application.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import matms.adapters.InMemoryParticipantRepository;
import matms.application.ParticipantService;
import matms.domain.MartialArt;
import matms.domain.entities.Participant;
import matms.domain.exceptions.InvalidWeightException;
import matms.domain.exceptions.ParticipantAlreadyExistsException;
import matms.domain.exceptions.ParticipantNotFoundException;
import matms.domain.repositories.ParticipantRepository;
import matms.domain.valueobjects.Adress;
import matms.domain.valueobjects.Id;
import matms.domain.valueobjects.Weight;

public class ParticipantServiceSpec {

	private ParticipantRepository participantRepo = new InMemoryParticipantRepository();

	@Test
	public void constructorTest() {
		ParticipantService participantService = new ParticipantService(participantRepo);
		assertFalse(participantService.equals(null));
	}

	@Test
	public void getByIdTest() throws ParticipantAlreadyExistsException, InvalidWeightException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Id id2 = new Id(UUID.randomUUID().toString());
		Adress adress = new Adress("a", "b", 1, 1);
		Weight weight = new Weight(1, "KG");
		List<MartialArt> arts = new ArrayList<>();
		arts.add(MartialArt.BOX);
		arts.add(MartialArt.KARATE);
		Participant p1 = Participant.builder().id(id1).firstName("a").lastName("b").adress(adress).weight(weight)
				.isLoser(false).payedFee(false).martialArts(arts).build();
		Participant p2 = Participant.builder().id(id2).firstName("a").lastName("b").adress(adress).weight(weight)
				.isLoser(false).payedFee(false).martialArts(arts).build();

		ParticipantService participantService = new ParticipantService(participantRepo);
		participantService.createParticipant(p1);
		participantService.createParticipant(p2);

		Optional<Participant> foundUser = participantService.getById(p1.getId().getUuid());

		assertTrue(foundUser.get().getId().equals(p1.getId()));
	}
	
	@Test
	public void createParticipantExceptionTest() throws ParticipantAlreadyExistsException, InvalidWeightException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Adress adress = new Adress("a", "b", 1, 1);
		Weight weight = new Weight(1, "KG");
		List<MartialArt> arts = new ArrayList<>();
		arts.add(MartialArt.BOX);
		arts.add(MartialArt.KARATE);
		Participant p1 = Participant.builder().id(id1).firstName("a").lastName("b").adress(adress).weight(weight)
				.isLoser(false).payedFee(false).martialArts(arts).build();
		Participant p2 = Participant.builder().id(id1).firstName("a").lastName("b").adress(adress).weight(weight)
				.isLoser(false).payedFee(false).martialArts(arts).build();

		ParticipantService participantService = new ParticipantService(participantRepo);
		participantService.createParticipant(p1);

		assertThrows(ParticipantAlreadyExistsException.class, () -> {
			participantService.createParticipant(p2);
		});
	}
	
	@Test
	public void getByMartialArtTest() throws InvalidWeightException, ParticipantAlreadyExistsException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Id id2 = new Id(UUID.randomUUID().toString());
		Adress adress = new Adress("a", "b", 1, 1);
		Weight weight = new Weight(1, "KG");
		List<MartialArt> arts = new ArrayList<>();
		arts.add(MartialArt.BOX);
		arts.add(MartialArt.BOX);
		Participant p1 = Participant.builder().id(id1).firstName("a").lastName("b").adress(adress).weight(weight)
				.isLoser(false).payedFee(false).martialArts(arts).build();
		Participant p2 = Participant.builder().id(id2).firstName("a").lastName("b").adress(adress).weight(weight)
				.isLoser(false).payedFee(false).martialArts(arts).build();

		ParticipantService participantService = new ParticipantService(participantRepo);
		participantService.createParticipant(p1);
		participantService.createParticipant(p2);

		List<Participant> foundUser = participantService.getByMartialArt(MartialArt.BOX);

		assertTrue(foundUser.size() == 2);
	}
	
	@Test
	public void updateParticipantExceptionTest() throws ParticipantAlreadyExistsException, InvalidWeightException, ParticipantNotFoundException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Id id2 = new Id(UUID.randomUUID().toString());
		Adress adress = new Adress("a", "b", 1, 1);
		Weight weight = new Weight(1, "KG");
		List<MartialArt> arts = new ArrayList<>();
		arts.add(MartialArt.BOX);
		arts.add(MartialArt.KARATE);
		Participant p1 = Participant.builder().id(id1).firstName("a").lastName("b").adress(adress).weight(weight)
				.isLoser(false).payedFee(false).martialArts(arts).build();
		Participant p2 = Participant.builder().id(id2).firstName("a").lastName("b").adress(adress).weight(weight)
				.isLoser(false).payedFee(false).martialArts(arts).build();

		ParticipantService participantService = new ParticipantService(participantRepo);
		participantService.createParticipant(p1);
		
		assertThrows(ParticipantNotFoundException.class, () -> {
			participantService.updateParticipant(p2);
		});
	}
	
	@Test
	public void updateParticipantTest() throws ParticipantAlreadyExistsException, InvalidWeightException, ParticipantNotFoundException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Adress adress = new Adress("a", "b", 1, 1);
		Weight weight = new Weight(1, "KG");
		List<MartialArt> arts = new ArrayList<>();
		arts.add(MartialArt.BOX);
		arts.add(MartialArt.KARATE);
		Participant p1 = Participant.builder().id(id1).firstName("a").lastName("b").adress(adress).weight(weight)
				.isLoser(false).payedFee(false).martialArts(arts).build();
		Participant p2 = Participant.builder().id(id1).firstName("a").lastName("c").adress(adress).weight(weight)
				.isLoser(false).payedFee(false).martialArts(arts).build();

		ParticipantService participantService = new ParticipantService(participantRepo);
		participantService.createParticipant(p1);
		
		participantService.updateParticipant(p2);
		
		assertTrue(participantService.getById(p2.getId().getUuid()).get().getLastName().equals("c"));
	}
	
	@Test
	public void deleteParticipantTest() throws ParticipantAlreadyExistsException, InvalidWeightException, ParticipantNotFoundException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Id id2 = new Id(UUID.randomUUID().toString());
		Adress adress = new Adress("a", "b", 1, 1);
		Weight weight = new Weight(1, "KG");
		List<MartialArt> arts = new ArrayList<>();
		arts.add(MartialArt.BOX);
		arts.add(MartialArt.KARATE);
		Participant p1 = Participant.builder().id(id1).firstName("a").lastName("b").adress(adress).weight(weight)
				.isLoser(false).payedFee(false).martialArts(arts).build();
		Participant p2 = Participant.builder().id(id2).firstName("a").lastName("c").adress(adress).weight(weight)
				.isLoser(false).payedFee(false).martialArts(arts).build();

		ParticipantService participantService = new ParticipantService(participantRepo);
		participantService.createParticipant(p1);
		participantService.createParticipant(p2);
		
		participantService.deleteParticipant(p2);
		
		assertTrue(participantRepo.getAllParticipants().size() == 1);
	}
}

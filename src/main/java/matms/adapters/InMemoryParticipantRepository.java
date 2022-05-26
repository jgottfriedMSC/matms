package matms.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import matms.domain.MartialArt;
import matms.domain.entities.Participant;
import matms.domain.repositories.ParticipantRepository;
import matms.domain.valueobjects.Id;

public class InMemoryParticipantRepository implements ParticipantRepository {

	private final Map<String, Participant> inMemParticipants = new HashMap<>();
	
	@Override
	public Optional<Participant> getById(String id) {
		return Optional.ofNullable(inMemParticipants.get(id));
	}

	@Override
	public List<Participant> getByMartialArt(MartialArt martialArt) {
		return inMemParticipants.values().stream()
				.filter(participant -> participant.getMartialArts().contains(martialArt))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Participant> getAllParticipants() {
		return new ArrayList<>(inMemParticipants.values());
	}

	@Override
	public void addParticipant(Participant participant) {
		inMemParticipants.put(participant.getId().getUuid(), participant);
	}

	@Override
	public void deleteParticipant(Participant participant) {
		inMemParticipants.remove(participant.getId().getUuid());
	}

	@Override
	public void updateParticipant(Participant participant) {
		inMemParticipants.put(participant.getId().getUuid(), participant);
	}

	@Override
	public Id nextId() {
		return new Id(UUID.randomUUID().toString());
	}

}

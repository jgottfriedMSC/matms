package matms.application;

import java.util.List;
import java.util.Optional;

import matms.domain.MartialArt;
import matms.domain.entities.Participant;
import matms.domain.exceptions.ParticipantAlreadyExistsException;
import matms.domain.exceptions.ParticipantNotFoundException;
import matms.domain.repositories.ParticipantRepository;

public final class ParticipantService {

	private final ParticipantRepository participantRepo;
	
	public ParticipantService(final ParticipantRepository participantRepo) {
		this.participantRepo = participantRepo;
	}
	
	public Optional<Participant> getById(final String id) {
		return participantRepo.getById(id);
	}
	
	public List<Participant> getByMartialArt(final MartialArt martialArt) {
		return participantRepo.getByMartialArt(martialArt);
	}
	
	public void createParticipant(final Participant participant) throws ParticipantAlreadyExistsException {
		if (participantRepo.getById(participant.getId().getUuid()).isPresent()) {
			throw new ParticipantAlreadyExistsException("Participant with id " + participant.getId().getUuid() + " already exists!");
		} else {
			Participant participantToSave = Participant.builder()
					.id(participant.getId())
					.lastName(participant.getLastName())
					.firstName(participant.getFirstName())
					.adress(participant.getAdress())
					.weight(participant.getWeight())
					.martialArts(participant.getMartialArts())
					.build();
			participantRepo.addParticipant(participantToSave);
		}
	}
	
	public void updateParticipant(final Participant participant) throws ParticipantNotFoundException {
		if (participantRepo.getById(participant.getId().getUuid()).isPresent()) {
			participantRepo.updateParticipant(participant);
		} else {
			throw new ParticipantNotFoundException("Participant " + participant.getFirstName() + " " + participant.getLastName() + " does not exist!");
		}
	}
	
	public void deleteParticipant(final Participant participant) {
		participantRepo.deleteParticipant(participant);
	}
}

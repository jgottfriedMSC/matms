package matms.domain.repositories;

import java.util.List;
import java.util.Optional;

import matms.domain.MartialArt;
import matms.domain.entities.Participant;
import matms.domain.valueobjects.Id;

public interface ParticipantRepository {

	Optional<Participant> getById(String id);
	
	List<Participant> getByMartialArt(MartialArt martialArt);
	
	List<Participant> getAllParticipants();
	
	void addParticipant(Participant participant);
	
	void deleteParticipant(Participant participant);
	
	void updateParticipant(Participant participant);
	
	Id nextId();
	
}

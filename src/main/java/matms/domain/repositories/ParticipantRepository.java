package matms.domain.repositories;

import java.util.List;

import matms.domain.MartialArt;
import matms.domain.Participant;

public interface ParticipantRepository {

	Participant getById(String id);
	
	List<Participant> getByMartialArt(MartialArt martialArt);
	
	void addParticipant(Participant participant);
	
	void removeParticipant(Participant participant);
	
	void updateParticipant(Participant participant);
	
}

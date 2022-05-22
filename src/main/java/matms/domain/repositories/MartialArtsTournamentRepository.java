package matms.domain.repositories;

import java.util.List;
import java.util.Optional;

import matms.domain.MartialArt;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.valueobjects.Id;

public interface MartialArtsTournamentRepository {

	Optional<MartialArtsTournament> getById(String id);
	
	Optional<MartialArtsTournament> getByName(String name);
	
	List<MartialArtsTournament> getAllTournaments();
	
	//List<MartialArtsTournament> findAllByTournamentMode(TournamentMode mode);
	
	List<MartialArtsTournament> findAllByMartialArt(MartialArt martialArt);
	
	void addTournament(MartialArtsTournament tournament);
		
	void removeTournament(MartialArtsTournament tournament);
		
	void updateTournament(MartialArtsTournament tournament);
	
	Id nextId();
}

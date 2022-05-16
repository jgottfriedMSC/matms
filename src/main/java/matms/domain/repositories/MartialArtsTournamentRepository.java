package matms.domain.repositories;

import java.util.List;

import matms.domain.MartialArt;
import matms.domain.MartialArtsTournament;
import matms.domain.TournamentMode;

public interface MartialArtsTournamentRepository {

	MartialArtsTournament getById(String id);
	
	MartialArtsTournament getByName(String name);
	
	List<MartialArtsTournament> getAllTournaments();
	
	List<MartialArtsTournament> findAllByTournamentMode(TournamentMode mode);
	
	List<MartialArtsTournament> findAllByMartialArt(MartialArt martialArt);
	
	void addTournament(MartialArtsTournament tournament);
		
	void removeTournament(MartialArtsTournament tournament);
		
	void updateTournament(MartialArtsTournament tournament);
	
	
}

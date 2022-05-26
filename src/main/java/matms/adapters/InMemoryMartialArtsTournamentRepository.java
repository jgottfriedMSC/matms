package matms.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import matms.domain.MartialArt;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.repositories.MartialArtsTournamentRepository;
import matms.domain.valueobjects.Id;

public class InMemoryMartialArtsTournamentRepository implements MartialArtsTournamentRepository {

	private final Map<String, MartialArtsTournament> inMemTournaments = new HashMap<>();
	
	@Override
	public Optional<MartialArtsTournament> getById(final String id) {
		return Optional.ofNullable(inMemTournaments.get(id));
	}

	@Override
	public Optional<MartialArtsTournament> getByName(final String name) {
		return inMemTournaments.values().stream()
				.filter(tournament -> tournament.getName().equals(name))
				.findAny();
	}

	@Override
	public List<MartialArtsTournament> getAllTournaments() {
		return new ArrayList<>(inMemTournaments.values());
	}

	@Override
	public List<MartialArtsTournament> findAllByMartialArt(final MartialArt martialArt) {
		return inMemTournaments.values().stream()
				.filter(tournament -> tournament.getMartialArt().equals(martialArt))
				.collect(Collectors.toList());
	}

	@Override
	public void addTournament(final MartialArtsTournament tournament) {
		inMemTournaments.put(tournament.getId().getUuid(), tournament);
	}

	@Override
	public void removeTournament(final MartialArtsTournament tournament) {
		inMemTournaments.remove(tournament.getId().getUuid());		
	}

	@Override
	public void updateTournament(final MartialArtsTournament tournament) {
		inMemTournaments.put(tournament.getId().getUuid(), tournament);
	}

	@Override
	public Id nextId() {
		return new Id(UUID.randomUUID().toString());
	}

}

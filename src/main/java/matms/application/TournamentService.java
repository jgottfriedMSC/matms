package matms.application;

import java.util.List;
import java.util.Optional;

import matms.domain.MartialArt;
import matms.domain.Permission;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.User;
import matms.domain.exceptions.AuthenticationException;
import matms.domain.exceptions.TournamentAlreadyExistsException;
import matms.domain.exceptions.TournamentNotFoundException;
import matms.domain.repositories.MartialArtsTournamentRepository;

public class TournamentService {

	private final User user;
	private final MartialArtsTournamentRepository martialArtsTournamentRepo;
	
	public TournamentService(final User user, final MartialArtsTournamentRepository martialArtsTournamentRepo) {
		this.user = user;
		this.martialArtsTournamentRepo = martialArtsTournamentRepo;
	}
	
	public Optional<MartialArtsTournament> getById(final String id) {
		return martialArtsTournamentRepo.getById(id);
	}
	
	public Optional<MartialArtsTournament> getByName(final String name) {
		return martialArtsTournamentRepo.getByName(name);
	}
	
	public List<MartialArtsTournament> getAllTournaments() {
		return martialArtsTournamentRepo.getAllTournaments();
	}
	
	public List<MartialArtsTournament> findAllByMartialArt(final MartialArt martialArt) {
		return martialArtsTournamentRepo.findAllByMartialArt(martialArt);
	}
	
	public void addTournament(final User user, final MartialArtsTournament tournament) throws AuthenticationException, TournamentAlreadyExistsException {
		if (user.getPermission() == Permission.TRAINER || user.getPermission() == Permission.ORGANIZER) {
			if (martialArtsTournamentRepo.getById(tournament.getId().getUuid()).isPresent()) {
				throw new TournamentAlreadyExistsException("Tournament already exists!");
			} else {
				var tournamentToSave = MartialArtsTournament.builder()
						.id(tournament.getId())
						.name(tournament.getName())
						.venue(tournament.getVenue())
						.entryFee(tournament.getEntryFee())
						.ticketPrice(tournament.getTicketPrice())
						.numberOfTicketsSold(tournament.getNumberOfTicketsSold())
						.participants(tournament.getParticipants())
						.martialArt(tournament.getMartialArt())
						.build();
				martialArtsTournamentRepo.addTournament(tournamentToSave);
			}

		} else {
			throw new AuthenticationException("User not allowed to create tournament!");
		}
	}
	
	public void removeTournament(MartialArtsTournament tournament) throws AuthenticationException, TournamentAlreadyExistsException {
		if (user.getPermission() == Permission.TRAINER || user.getPermission() == Permission.ORGANIZER) {
			if (martialArtsTournamentRepo.getById(tournament.getId().getUuid()).isPresent()) {
				throw new TournamentAlreadyExistsException("Tournament already exists!");
			} else {
				martialArtsTournamentRepo.removeTournament(tournament);
			}
			
		} else {
			throw new AuthenticationException("User not allowed to remove tournament!");
		}
	}
	
	public void updateTournament(MartialArtsTournament tournament) throws AuthenticationException, TournamentNotFoundException {
		if (user.getPermission() == Permission.TRAINER || user.getPermission() == Permission.ORGANIZER) {
			if (martialArtsTournamentRepo.getById(tournament.getId().getUuid()).isPresent()) {
				var tournamentToSave = MartialArtsTournament.builder()
						.id(tournament.getId())
						.name(tournament.getName())
						.venue(tournament.getVenue())
						.entryFee(tournament.getEntryFee())
						.ticketPrice(tournament.getTicketPrice())
						.numberOfTicketsSold(tournament.getNumberOfTicketsSold())
						.participants(tournament.getParticipants())
						.martialArt(tournament.getMartialArt())
						.build();
				martialArtsTournamentRepo.updateTournament(tournamentToSave);
			} else {
				throw new TournamentNotFoundException("Tournament not found!");
			}

		} else {
			throw new AuthenticationException("User not allowed to update tournament!");
		}
	}

	
}

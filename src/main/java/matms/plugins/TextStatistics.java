package matms.plugins;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map.Entry;

import matms.adapters.RoundService;
import matms.application.KnockoutMode;
import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.Participant;
import matms.domain.entities.User;
import matms.domain.exceptions.NoWinnerException;
import matms.domain.exceptions.TournamentNotFoundException;
import matms.domain.repositories.MartialArtsTournamentRepository;
import matms.domain.repositories.UserRepository;

public class TextStatistics {

	private RoundService roundService;
	private UserRepository userRepository;
	private MartialArtsTournamentRepository tournamentRepository;
	
	public TextStatistics(RoundService roundService, UserRepository userRepository, MartialArtsTournamentRepository tournamentRepository) {
		this.userRepository = userRepository;
		this.tournamentRepository = tournamentRepository;
		this.roundService = roundService;
	}
	
	private String getUserData() {
		List<User> users = userRepository.getAllUsers();
		StringBuilder sb = new StringBuilder();
		sb.append("Users:\n");
		sb.append("--------------------------\n");
		for (User user : users) {
			sb.append(user.toString());
			sb.append("\n");
		}
		return sb.toString();
	} 
	
	private String getTournamentData() throws NoWinnerException, TournamentNotFoundException {
		List<MartialArtsTournament> tournaments = tournamentRepository.getAllTournaments();
		StringBuilder sb = new StringBuilder();
		sb.append("Tournaments:\n");
		sb.append("--------------------------\n");
		for (MartialArtsTournament tournament : tournaments) {
			sb.append(tournament.toString());
			sb.append("\n");
			if (roundService.getTournamentMode() instanceof KnockoutMode) {
				sb.append(roundService.getTournamentWinner().toString());
			} else {
				for (Entry<Participant, Integer> participants : roundService.getParticipantPoints().entrySet()) {
					sb.append(participants.getKey().toString() + ", Points: " + participants.getValue());
					sb.append("\n");
				}
			}
		}
		return sb.toString();
	}
	
	private String getWinner() throws NoWinnerException, TournamentNotFoundException {
		StringBuilder sb = new StringBuilder();
		sb.append("Winner:\n");
		sb.append("--------------------------\n");
		sb.append(roundService.getTournamentWinner().toString());
		return sb.toString();
	}
	
	public void createDataOutputFile() throws FileNotFoundException, NoWinnerException, TournamentNotFoundException {
		File outputFile = new File("TournamentData.txt");
		try (PrintWriter pw = new PrintWriter(outputFile)) {
			pw.print(getUserData());
			pw.print("\n");
			pw.print(getTournamentData());
			pw.print("\n");
			pw.print(getWinner());
		}
	}
	
	
}

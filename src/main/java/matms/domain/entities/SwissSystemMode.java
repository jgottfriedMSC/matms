package matms.domain.entities;

import matms.abstraction.Utils;
import matms.domain.aggregates.MartialArtsTournament;

public class SwissSystemMode implements TournamentMode {

	private MartialArtsTournament tournament;
	
	public SwissSystemMode(MartialArtsTournament tournament) {
		this.tournament = tournament;
	}

	@Override
	public int calculateNumberOfRounds() {
		return (int) ((tournament.getParticipants().size() / 2) * Utils.customLog(2, tournament.getParticipants().size())); // n/2 * log_2(n);
	}
	
}

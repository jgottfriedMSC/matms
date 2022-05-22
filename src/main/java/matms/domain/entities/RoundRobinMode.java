package matms.domain.entities;

import matms.abstraction.Utils;
import matms.domain.aggregates.MartialArtsTournament;

public class RoundRobinMode implements TournamentMode {

	private MartialArtsTournament tournament;
	
	public RoundRobinMode(MartialArtsTournament tournament) {
		this.tournament = tournament;
	}
	
	@Override
	public int calculateNumberOfRounds() {
		return (Utils.factCalculator(tournament.getParticipants().size())) / (Utils.factCalculator(2)) * Utils.factCalculator(tournament.getParticipants().size() - 2); // Binomialkoeffizient;
	}

}

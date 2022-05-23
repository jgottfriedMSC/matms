package matms.domain.entities;

import matms.abstraction.MathUtils;
import matms.domain.aggregates.MartialArtsTournament;

public class RoundRobinMode implements TournamentMode {

	private MartialArtsTournament tournament;
	
	public RoundRobinMode(MartialArtsTournament tournament) {
		this.tournament = tournament;
	}
	
	@Override
	public int calculateNumberOfRounds() {
		return (MathUtils.factCalculator(tournament.getParticipants().size())) / (MathUtils.factCalculator(2)) * MathUtils.factCalculator(tournament.getParticipants().size() - 2); // Binomialkoeffizient;
	}

}

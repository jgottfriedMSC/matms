package matms.domain.entities;

import matms.domain.aggregates.MartialArtsTournament;

public class DoubleKnockoutMode implements TournamentMode {

	private MartialArtsTournament tournament;
	
	public DoubleKnockoutMode(MartialArtsTournament tournament) {
		this.tournament = tournament;
	}
	
	@Override
	public int calculateNumberOfRounds() {
		return tournament.getParticipants().size() - 1; // n - 1
	}

	
	
}

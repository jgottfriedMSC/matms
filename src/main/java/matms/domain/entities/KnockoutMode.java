package matms.domain.entities;

import matms.domain.aggregates.MartialArtsTournament;

public class KnockoutMode implements TournamentMode {

	private MartialArtsTournament tournament;
	
	public KnockoutMode(MartialArtsTournament tournament) {
		this.tournament = tournament;
	}
	
	@Override
	public int calculateNumberOfRounds() {
		return tournament.getParticipants().size() - 1; // n - 1
	}

	
	
}

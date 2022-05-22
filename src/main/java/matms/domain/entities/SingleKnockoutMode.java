package matms.domain.entities;

import matms.domain.aggregates.MartialArtsTournament;

public class SingleKnockoutMode implements TournamentMode {

	private MartialArtsTournament tournament;
	
	public SingleKnockoutMode(MartialArtsTournament tournament) {
		this.tournament = tournament;
	}
	
	@Override
	public int calculateNumberOfRounds() {
		return tournament.getParticipants().size() - 1; // n - 1
	}

}

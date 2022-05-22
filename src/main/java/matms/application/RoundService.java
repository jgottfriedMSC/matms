package matms.application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import matms.domain.aggregates.MartialArtsTournament;
import matms.domain.entities.Participant;
import matms.domain.entities.TournamentMode;
import matms.domain.valueobjects.Round;

public class RoundService {

	private List<Round> rounds;
	private TournamentMode tournamentMode;
	private int numberOfRounds;
	private MartialArtsTournament tournament;
	
	public RoundService(MartialArtsTournament tournament, TournamentMode tournamentMode) {
		this.rounds = new ArrayList<>();
		this.tournament = tournament;
		this.tournamentMode = tournamentMode;
		this.numberOfRounds = tournamentMode.calculateNumberOfRounds();
	}
	
	public void initializeFirstEncounters() {
		Iterator<Participant> it = tournament.getParticipants().iterator();
		
		while (it.hasNext()) {
			Participant participant = it.next();
			Participant opponent = it.next();
			rounds.add(new Round(participant, opponent));
		}
		for (Round r : rounds) {
			System.out.println(r.getParticipant().getFirstName() + " " + r.getOpponent().getFirstName());
		}
		
	}
	
	public int getNumberOfRounds() {
		return numberOfRounds;
	}
	
}

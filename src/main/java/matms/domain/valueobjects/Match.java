package matms.domain.valueobjects;

import java.util.Objects;

import matms.domain.entities.Participant;

public final class Match {

	private final Participant participant;
	private final Participant opponent;
	
	public Match(final Participant participant, final Participant opponent) {
		this.participant = participant;
		this.opponent = opponent;
	}
	
	public Participant getParticipant() {
		return participant;
	}
	
	public Participant getOpponent() {
		return opponent;
	}
	
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		
		Match round = (Match) o;
		
		return (Objects.equals(participant, round.participant) && Objects.equals(opponent, round.opponent));
	}
	
	public int hashCode() {
		return Objects.hash(participant, opponent);
	}
	
	public void defineLoser(Participant loser) {
		loser.lose();
	}
	
}

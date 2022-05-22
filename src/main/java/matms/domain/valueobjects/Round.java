package matms.domain.valueobjects;

import java.util.Objects;

import matms.domain.entities.Participant;

public final class Round {

	private final Participant participant;
	private final Participant opponent;
	
	public Round(final Participant participant, final Participant opponent) {
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
		
		Round round = (Round) o;
		
		if (Objects.equals(participant, round.participant) && Objects.equals(opponent, round.opponent)) {
			return true;
		} else {
			return false;
		}
	}
	
	public int hashCode() {
		return Objects.hash(participant, opponent);
	}
	
}

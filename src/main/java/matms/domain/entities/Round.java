package matms.domain.entities;

import java.util.List;

import matms.domain.valueobjects.Match;

public class Round {

	private List<Match> matches;
	
	public Round(List<Match> matches) {
		this.matches = matches;
	}
	
	public List<Match> getMatches() {
		return matches;
	}
}

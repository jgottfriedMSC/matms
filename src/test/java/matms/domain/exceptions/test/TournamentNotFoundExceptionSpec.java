package matms.domain.exceptions.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import matms.domain.exceptions.TournamentNotFoundException;

public class TournamentNotFoundExceptionSpec {

	@Test
	public void tournamentNotFoundExceptionTest() {
		TournamentNotFoundException ex = new TournamentNotFoundException("Tournament not found");
		assertTrue(ex.getMessage().equals("Tournament not found"));
	}
	
}

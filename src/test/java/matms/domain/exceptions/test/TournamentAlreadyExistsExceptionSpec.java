package matms.domain.exceptions.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import matms.domain.exceptions.TournamentAlreadyExistsException;

public class TournamentAlreadyExistsExceptionSpec {

	@Test
	public void tournamentAlreadyExistsExceptionTest() {
		TournamentAlreadyExistsException ex = new TournamentAlreadyExistsException("Tournament already exists");
		assertTrue(ex.getMessage().equals("Tournament already exists"));
	}
}

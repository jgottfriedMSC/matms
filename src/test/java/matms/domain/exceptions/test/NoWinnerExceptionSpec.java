package matms.domain.exceptions.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import matms.domain.exceptions.NoWinnerException;

public class NoWinnerExceptionSpec {

	@Test
	public void noWinnerExceptionTest() {
		NoWinnerException ex = new NoWinnerException("No Winner");
		assertTrue(ex.getMessage().equals("No Winner"));
	}
}

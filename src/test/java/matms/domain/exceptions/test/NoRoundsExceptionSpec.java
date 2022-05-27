package matms.domain.exceptions.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import matms.domain.exceptions.NoRoundsException;

public class NoRoundsExceptionSpec {

	@Test
	public void noRoundsExceptionTest() {
		NoRoundsException ex = new NoRoundsException("No Rounds");
		assertTrue(ex.getMessage().equals("No Rounds"));
	}
	
}

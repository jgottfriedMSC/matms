package matms.domain.exceptions.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import matms.domain.exceptions.InvalidWeightException;

public class InvalidWeightExceptionSpec {

	@Test
	public void invalidWeightExceptionTest() {
		InvalidWeightException ex = new InvalidWeightException("Invalid Weight");
		assertTrue(ex.getMessage().equals("Invalid Weight"));
	}
	
}

package matms.domain.exceptions.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import matms.domain.exceptions.InvalidAmountException;

public class InvalidAmountExceptionTest {

	@Test
	public void invalidAmountExceptionTest() {
		InvalidAmountException ex = new InvalidAmountException("Invalid Amount");
		assertTrue(ex.getMessage().equals("Invalid Amount"));
	}
	
}

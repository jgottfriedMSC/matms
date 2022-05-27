package matms.domain.exceptions.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import matms.domain.exceptions.UserAlreadyExistsException;

public class UserAlreadyExistsExceptionSpec {

	@Test
	public void userAlreadyExistsExceptionTest() {
		UserAlreadyExistsException ex = new UserAlreadyExistsException("User already exists");
		assertTrue(ex.getMessage().equals("User already exists"));
	}
}

package matms.domain.exceptions.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import matms.domain.exceptions.UserNotFoundException;

public class UserNotFoundExceptionTest {

	@Test
	public void userNotFoundExceptionTest() {
		UserNotFoundException ex = new UserNotFoundException("User not found");
		assertTrue(ex.getMessage().equals("User not found"));
	}
	
}

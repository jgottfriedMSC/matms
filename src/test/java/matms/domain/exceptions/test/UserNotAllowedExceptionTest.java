package matms.domain.exceptions.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import matms.domain.exceptions.UserNotAllowedException;

public class UserNotAllowedExceptionTest {

	@Test
	public void userNotAllowedExceptionTest() {
		UserNotAllowedException ex = new UserNotAllowedException("User not allowed");
		assertTrue(ex.getMessage().equals("User not allowed"));
	}
	
}

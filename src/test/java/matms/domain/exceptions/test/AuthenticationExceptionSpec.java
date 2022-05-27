package matms.domain.exceptions.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import matms.domain.exceptions.AuthenticationException;

public class AuthenticationExceptionSpec {

	@Test
	public void authenticationExceptionTest() {
		AuthenticationException ex = new AuthenticationException("Authentication Error");
		assertTrue(ex.getMessage().equals("Authentication Error"));
	}
	
}

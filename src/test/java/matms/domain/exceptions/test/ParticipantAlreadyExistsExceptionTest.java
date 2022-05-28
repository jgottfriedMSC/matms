package matms.domain.exceptions.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import matms.domain.exceptions.ParticipantAlreadyExistsException;

public class ParticipantAlreadyExistsExceptionTest {

	@Test
	public void participantAlreadyExistsExceptionTest() {
		ParticipantAlreadyExistsException ex = new ParticipantAlreadyExistsException("Participant already exists");
		assertTrue(ex.getMessage().equals("Participant already exists"));
	}
	
}

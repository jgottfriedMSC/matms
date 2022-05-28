package matms.domain.exceptions.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import matms.domain.exceptions.ParticipantNotFoundException;

public class ParticipantNotFoundExceptionTest {

	@Test
	public void participantNotFoundExceptionTest() {
		ParticipantNotFoundException ex = new ParticipantNotFoundException("Participant not found");
		assertTrue(ex.getMessage().equals("Participant not found"));
	}
	
}

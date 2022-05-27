package matms.domain.valueobjects.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import matms.domain.entities.Participant;
import matms.domain.valueobjects.Id;
import matms.domain.valueobjects.Match;

public class MatchSpec {

	@Test
	public void equalsNullAndFalseClassTest() {
		Participant p1 = Mockito.mock(Participant.class);
		Participant p2 = Mockito.mock(Participant.class);
		Match match = new Match(p1, p2);
		assertFalse(match.equals(null));
		assertFalse(match.equals(new Id(null)));
	}
	
	@Test
	public void equalsAttributeTest() {
		Participant p1 = Mockito.mock(Participant.class);
		Participant p2 = Mockito.mock(Participant.class);
		Match match1 = new Match(p1, p2);
		
		Participant p3 = Mockito.mock(Participant.class);
		Participant p4 = Mockito.mock(Participant.class);
		Match match2 = new Match(p3, p4);
		Match match3 = new Match(p3, p4);
		
		assertFalse(match1.equals(match2));
		assertFalse(match2.equals(match1));
		assertTrue(match2.equals(match3));
		assertTrue(match2.getOpponent().equals(match3.getOpponent()));
		assertTrue(match2.getParticipant().equals(match3.getParticipant()));
	}
	
	@Test
	public void hashCodeTest() {
		Participant p1 = Mockito.mock(Participant.class);
		Participant p2 = Mockito.mock(Participant.class);
		Match match1 = new Match(p1, p2);
		
		Participant p3 = Mockito.mock(Participant.class);
		Participant p4 = Mockito.mock(Participant.class);
		Match match2 = new Match(p3, p4);
		Match match3 = new Match(p3, p4);
		
		assertFalse(match1.hashCode() == match2.hashCode());
		assertTrue(match2.hashCode() == match3.hashCode());
	}
	
	@Test
	public void defineLoserTest() {
		Match match = Mockito.mock(Match.class);
		Participant p = Mockito.mock(Participant.class);
		
		match.defineLoser(p);
		Mockito.verify(match, Mockito.times(1)).defineLoser(p);
	
	}
	
}

package matms.domain.valueobjects.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import matms.domain.valueobjects.Adress;
import matms.domain.valueobjects.Id;

public class IdTest {

	@Test
	public void equalsNullAndFalseClassTest() {
		Id id = new Id(UUID.randomUUID().toString());
		assertFalse(id.equals(null));
		assertFalse(id.equals(new Adress(null, null, 1, 1)));
	}
	
	@Test
	public void equalsAttributeTest() {
		Id id1 = new Id(UUID.randomUUID().toString());
		Id id2 = new Id(UUID.randomUUID().toString());
		Id id3 = new Id(id2.getUuid());
		
		assertFalse(id1.equals(id2));
		assertFalse(id2.equals(id1));
		assertTrue(id2.equals(id3));
	}
	
	@Test
	public void hashCodeTest() {
		Id id1 = new Id(UUID.randomUUID().toString());
		Id id2 = new Id(UUID.randomUUID().toString());
		Id id3 = new Id(id2.getUuid());
		assertFalse(id1.hashCode() == id2.hashCode());
		assertTrue(id2.hashCode() == id3.hashCode());
	}
	
}

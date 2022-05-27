package matms.domain.tests;

import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.Test;

import matms.domain.valueobjects.Adress;
import matms.domain.valueobjects.Id;

public class AdressSpec {
	
	@Test
	public void equalsNullTest() {
		Adress adress = new Adress(null, null, 0, 0);
		assertFalse(adress.equals(null));
	}
	
	@Test
	public void equalsFalseClassTest() {
		Adress adress = new Adress(null, null, 0, 0);
		assertFalse(adress.equals(new Id(null)));
	}
	
	@Test
	public void equalsNullAndFalseClassTest() {
		
	}
	
}

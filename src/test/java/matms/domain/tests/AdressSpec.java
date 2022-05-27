package matms.domain.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import matms.domain.valueobjects.Adress;
import matms.domain.valueobjects.Id;

public class AdressSpec {
	
	@Test
	public void equalsNullAndFalseClassTest() {
		Adress adress = new Adress(null, null, 0, 0);
		assertFalse(adress.equals(null));
		assertFalse(adress.equals(new Id(null)));
	}
	
	@Test
	public void equalsAttributeTest() {
		Adress adress1 = new Adress("a", "b", 1, 1);
		Adress adress2 = new Adress("b", "a", 2, 2);
		Adress adress3 = new Adress("b", "a", 2, 2);
		assertFalse(adress1.equals(adress2));
		assertFalse(adress2.equals(adress1));
		assertTrue(adress2.equals(adress3));
	}
	
	@Test
	public void getCountryTest() {
		Adress adress = new Adress("a", "d", 1, 1);
		String country = "a";
		assertTrue(adress.getCountry().equals(country));
	}
	
}

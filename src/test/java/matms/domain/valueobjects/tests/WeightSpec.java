package matms.domain.valueobjects.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import matms.domain.exceptions.InvalidWeightException;
import matms.domain.valueobjects.Id;
import matms.domain.valueobjects.Weight;

public class WeightSpec {

	@Test
	public void equalsNullAndFalseClassTest() throws InvalidWeightException {
		Weight weight = new Weight(1, "KG");
		assertFalse(weight.equals(null));
		assertFalse(weight.equals(new Id(null)));
	}
	
	@Test
	public void constructorExceptionTest() {
		assertThrows(InvalidWeightException.class, () -> {
			new Weight(-1, null);
		});
	}
	
	@Test
	public void equalsAttributeTest() throws InvalidWeightException {
		Weight weight1 = new Weight(1, "KG");
		Weight weight2 = new Weight(2, "KG");
		Weight weight3 = new Weight(2, "KG");
		
		assertFalse(weight1.equals(weight2));
		assertFalse(weight2.equals(weight1));
		assertTrue(weight2.equals(weight3));
	}
	
	@Test
	public void changeWeightTest() throws InvalidWeightException {
		Weight weight = new Weight(0, null);
		Weight newWeight = weight.changeWeight(2, "KG");
		assertTrue(newWeight.getWeight() == 2.0 && newWeight.getUnit().equals("KG"));
		
	}
	
	@Test
	public void hashCodeTest() throws InvalidWeightException {
		Weight weight1 = new Weight(1, "KG");
		Weight weight2 = new Weight(2, "KG");
		Weight weight3 = new Weight(2, "KG");
		assertFalse(weight1.hashCode() == weight2.hashCode());
		assertTrue(weight2.hashCode() == weight3.hashCode());
	}
	
	@Test
	public void toStringTest() throws InvalidWeightException {
		String weightString = "70.0 KG";
		Weight weight = new Weight(70, "KG");
		assertTrue(weight.toString().equals(weightString));
	}
	
}

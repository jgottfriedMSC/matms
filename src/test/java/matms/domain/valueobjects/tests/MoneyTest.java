package matms.domain.valueobjects.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import matms.domain.exceptions.InvalidAmountException;
import matms.domain.valueobjects.Id;
import matms.domain.valueobjects.Money;

public class MoneyTest {

	@Test
	public void equalsNullAndFalseClassTest() throws InvalidAmountException {
		Money money = new Money(2.0, "EUR");
		assertFalse(money.equals(null));
		assertFalse(money.equals(new Id(null)));
	}
	
	@Test
	public void constructorExceptionTest() {
		assertThrows(InvalidAmountException.class, () -> {
			new Money(-1, null);
		});
	}
	
	@Test
	public void equalsAttributeTest() throws InvalidAmountException {
		Money money1 = new Money(1, "EUR");
		Money money2 = new Money(2, "EUR");
		Money money3 = new Money(2, "EUR");
		
		assertFalse(money1.equals(money2));
		assertFalse(money2.equals(money1));
		assertTrue(money2.equals(money3));
	}
	
	@Test
	public void changeMoneyTest() throws InvalidAmountException {
		Money money = new Money(0, null);
		Money newMoney = money.changeValue(2, "EUR");
		assertTrue(newMoney.getAmount() == 2.0 && newMoney.getCurrency().equals("EUR"));
	}
	
	@Test
	public void hashCodeTest() throws InvalidAmountException {
		Money money1 = new Money(1, "EUR");
		Money money2 = new Money(2, "EUR");
		Money money3 = new Money(2, "EUR");
		assertFalse(money1.hashCode() == money2.hashCode());
		assertTrue(money2.hashCode() == money3.hashCode());
	}
	
	@Test
	public void toStringTest() throws InvalidAmountException {
		String moneyString = "2.0 EUR";
		Money money = new Money(2.0, "EUR");
		assertTrue(money.toString().equals(moneyString));
	}
	
}

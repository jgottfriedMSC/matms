package matms.domain;

import java.util.Objects;

public final class Money {

	private final double amount;
	private final String currency;
	
	public Money(final double amount, final String currency) {
		this.amount = amount;
		this.currency = currency;
	}
	
	public Money changeValue(final double amount, final String currency) {
		return new Money(amount, currency);
	}
	
	public double getAmount() {
		return amount;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	@Override
	public String toString() {
		return amount + " " + currency;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Money m = (Money) o;
		if (Objects.equals(amount, m.amount) && Objects.equals(currency, m.currency)) {
			return true;
		} else {
			return false;
		}
	}
	
}

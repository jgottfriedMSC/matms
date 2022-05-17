package matms.domain.valueobjects;

import java.util.Objects;

import matms.domain.exceptions.InvalidAmountException;

public final class Money {

	//TODO: conversion ofEuro, ofPound, ofDollar, ...
	private final double amount;
	private final String currency;
	
	public Money(final double amount, final String currency) throws InvalidAmountException {
		if (amount < 0.00) {
			throw new InvalidAmountException("Amount should be >= 0.00");
		}
		this.amount = amount;
		this.currency = currency;
	}
	
	public Money changeValue(final double amount, final String currency) throws InvalidAmountException {
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

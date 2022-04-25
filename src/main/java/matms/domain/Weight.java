package matms.domain;

import java.util.Objects;

import matms.domain.exception.InvalidWeightException;

public final class Weight {

	//TODO: conversion methods ofKilogram, ofGram
	private final double weight;
	private final String unit;
	
	public Weight(final double weight, final String unit) throws InvalidWeightException {
		if (weight < 0.00) {
			throw new InvalidWeightException("Weight should be >= 0.00");
		}
		this.weight = weight;
		this.unit = unit;
	}
	
	public Weight changeWeight(final double weight, final String unit) throws InvalidWeightException {
		return new Weight(weight, unit);
	}
	
	public double getWeight() {
		return weight;
	}
	
	public String getUnit() {
		return unit;
	}
	
	@Override
	public String toString() {
		return weight + " " + unit;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Weight w = (Weight) o;
		if (Objects.equals(weight, w.weight) && Objects.equals(unit, w.unit)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(weight, unit);
	}
} 

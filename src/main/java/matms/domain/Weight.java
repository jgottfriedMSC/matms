package matms.domain;

import java.util.Objects;

public final class Weight {

	private final double weight;
	private final String unit;
	
	public Weight(final double weight, final String unit) {
		this.weight = weight;
		this.unit = unit;
	}
	
	public Weight changeWeight(final double weight, final String unit) {
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
		return weight + unit;
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

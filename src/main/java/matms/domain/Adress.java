package matms.domain;

import java.util.Objects;

public final class Adress {

	// TODO: check for ISO-Standard to create a list of country codes
	private final String country;
	private final String street;
	private final int postalCode;
	private final int streetNumber;

	public Adress(final String country, final String street, final int postalCode, final int streetNumber) {
		this.country = country;
		this.street = street;
		this.postalCode = postalCode;
		this.streetNumber = streetNumber;
	}

	public String getCountry() {
		return this.country;
	}

	public String getStreet() {
		return this.street;
	}

	public int getPostalCode() {
		return this.postalCode;
	}

	public int getStreetNumber() {
		return this.streetNumber;
	}

	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		Adress v = (Adress) o;
		if (Objects.equals(country, v.country) && Objects.equals(street, v.street)
				&& Objects.equals(postalCode, v.postalCode) && Objects.equals(streetNumber, v.streetNumber)) {
			return true;
		} else {
			return false;
		}
	}

	public int hashCode() {
		return Objects.hash(country, street, postalCode, streetNumber);
	}

}

package matms.domain.valueobjects;

import java.util.Objects;

public final class Id {

	private final String uuid;
	
	public Id(final String uuid) {
		this.uuid = uuid;
	}
	
	public String getUuid() {
		return this.uuid;
	}
	
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		
		Id id = (Id) o;
		
		if (Objects.equals(uuid, id.uuid)) {
			return true;
		} else {
			return false;
		}
	}
	
	public int hashCode() {
		return Objects.hash(uuid);
	}
}

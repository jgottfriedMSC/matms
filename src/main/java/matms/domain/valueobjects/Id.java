package matms.domain.valueobjects;

import java.util.Objects;
import java.util.UUID;

public final class Id {

	private final UUID uuid;
	
	public Id(final UUID uuid) {
		this.uuid = uuid;
	}
	
	public UUID getId() {
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

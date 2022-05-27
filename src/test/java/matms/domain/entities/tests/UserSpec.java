package matms.domain.entities.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import matms.domain.Permission;
import matms.domain.entities.User;
import matms.domain.exceptions.InvalidWeightException;
import matms.domain.valueobjects.Id;

public class UserSpec {

	private static User user;
	private static Id id;
	
	@BeforeAll
	public static void initializeParticipant() {
		id = Mockito.mock(Id.class);
		
		user = User.builder()
							.id(id)
							.username("test")
							.password("password")
							.firstName("a")
							.lastName("b")
							.permission(Permission.ORGANIZER)
							.build();
	}
	
	@Test
	public void builderTest() {
		assertTrue(user.getFirstName().equals("a"));
		assertTrue(user.getLastName().equals("b"));
		assertTrue(user.getId().equals(id));
		assertTrue(user.getPassword().equals("password"));
		assertTrue(user.getUsername().equals("test"));
		assertTrue(user.getPermission().equals(Permission.ORGANIZER));
	}
	
	@Test
	public void toStringWithOneArtTest() throws InvalidWeightException {
		Id id = new Id(UUID.randomUUID().toString());
		User user = User.builder()
				.id(id)
				.username("test")
				.password("password")
				.firstName("a")
				.lastName("b")
				.permission(Permission.ORGANIZER)
				.build();
		String userString = "User{" +
				"id='" + id.getUuid().toString() + "'" +
				", username='test'" +
				", password='password'" +
				", lastName='b'" +
				", firstName='a'" +
				", permission='ORGANIZER'}";
		assertTrue(user.toString().equals(userString));
	}
	
}

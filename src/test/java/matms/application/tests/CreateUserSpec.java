package matms.application.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import matms.adapters.InMemoryUserRepository;
import matms.application.CreateUser;
import matms.domain.Permission;
import matms.domain.entities.User;
import matms.domain.exceptions.UserAlreadyExistsException;
import matms.domain.repositories.UserRepository;
import matms.domain.valueobjects.Id;

public class CreateUserSpec {

	private UserRepository userRepo = new InMemoryUserRepository();
	
	@Test
	public void constructorTest() {
		CreateUser createUser = new CreateUser(userRepo);
		assertFalse(createUser.equals(null));
	}
	
	@Test
	public void createExceptionTest() throws UserAlreadyExistsException {
		Id id = new Id(UUID.randomUUID().toString());
		User user1 = User.builder()
				.id(id)
				.username("test")
				.password("password")
				.firstName("a")
				.lastName("b")
				.permission(Permission.ORGANIZER)
				.build();
		User user2 = User.builder()
				.id(id)
				.username("test")
				.password("password")
				.firstName("a")
				.lastName("b")
				.permission(Permission.ORGANIZER)
				.build();
		
		CreateUser createUser = new CreateUser(userRepo);
		createUser.create(user1);
		
		assertThrows(UserAlreadyExistsException.class, () -> {
			createUser.create(user2);
		});
	}
	
}

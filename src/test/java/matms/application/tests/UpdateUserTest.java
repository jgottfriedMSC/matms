package matms.application.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import matms.adapters.InMemoryUserRepository;
import matms.application.CreateUser;
import matms.application.UpdateUser;
import matms.domain.Permission;
import matms.domain.entities.User;
import matms.domain.exceptions.UserAlreadyExistsException;
import matms.domain.exceptions.UserNotFoundException;
import matms.domain.repositories.UserRepository;
import matms.domain.valueobjects.Id;

public class UpdateUserTest {

	private UserRepository userRepo = new InMemoryUserRepository();
	
	@Test
	public void constructorTest() {
		UpdateUser updateUser = new UpdateUser(userRepo);
		assertFalse(updateUser.equals(null));
	}
	
	@Test
	public void updateExceptionTest() throws UserAlreadyExistsException, UserNotFoundException {
		Id id1 = new Id(UUID.randomUUID().toString());
		Id id2 = new Id(UUID.randomUUID().toString());
		User user1 = User.builder()
				.id(id1)
				.username("test")
				.password("password")
				.firstName("a")
				.lastName("b")
				.permission(Permission.ORGANIZER)
				.build();
		User user2 = User.builder()
				.id(id2)
				.username("test")
				.password("password")
				.firstName("a")
				.lastName("b")
				.permission(Permission.ORGANIZER)
				.build();
		
		CreateUser createUser = new CreateUser(userRepo);
		createUser.create(user1);
		
		UpdateUser updateUser = new UpdateUser(userRepo);
		
		assertThrows(UserNotFoundException.class, () -> {
			updateUser.updateUser(user2);
		});
	}
	
	@Test
	public void updateTest() throws UserAlreadyExistsException, UserNotFoundException {
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
		
		UpdateUser updateUser = new UpdateUser(userRepo);
		updateUser.updateUser(user2);
		
		assertTrue(userRepo.getById(user2.getId().getUuid()).get().getId().getUuid().equals(user1.getId().getUuid()));
	}
}

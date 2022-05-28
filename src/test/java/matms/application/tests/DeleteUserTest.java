package matms.application.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import matms.adapters.InMemoryUserRepository;
import matms.application.CreateUser;
import matms.application.DeleteUser;
import matms.domain.Permission;
import matms.domain.entities.User;
import matms.domain.exceptions.UserAlreadyExistsException;
import matms.domain.repositories.UserRepository;
import matms.domain.valueobjects.Id;

public class DeleteUserTest {
	private UserRepository userRepo = new InMemoryUserRepository();
	
	@Test
	public void constructorTest() {
		DeleteUser deleteUser = new DeleteUser(userRepo);
		assertFalse(deleteUser.equals(null));
	}
	
	@Test
	public void deleteUserTest() throws UserAlreadyExistsException {
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
		createUser.create(user2);
		
		DeleteUser deleteUser = new DeleteUser(userRepo);
		deleteUser.deleteUser(user1);
		
		assertTrue(userRepo.getAllUsers().size() == 1);
	}
}

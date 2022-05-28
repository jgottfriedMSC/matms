package matms.application.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import matms.adapters.InMemoryUserRepository;
import matms.application.CreateUser;
import matms.application.FindUser;
import matms.domain.Permission;
import matms.domain.entities.User;
import matms.domain.exceptions.UserAlreadyExistsException;
import matms.domain.repositories.UserRepository;
import matms.domain.valueobjects.Id;

public class FindUserTest {

	private UserRepository userRepo = new InMemoryUserRepository();
	
	@Test
	public void constructorTest() {
		FindUser findUser = new FindUser(userRepo);
		assertFalse(findUser.equals(null));
	}
	
	@Test
	public void getByIdTest() throws UserAlreadyExistsException {
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
		
		FindUser findUser = new FindUser(userRepo);
		Optional<User> foundUser = findUser.getById(user1.getId().getUuid());
		
		assertTrue(foundUser.get().getId().equals(user1.getId()));
	}
	
	@Test
	public void getByPermissionTest() throws UserAlreadyExistsException {
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
		
		FindUser findUser = new FindUser(userRepo);
		List<User> users = findUser.getByPermission(Permission.ORGANIZER);
		
		assertTrue(users.size() == 2);
	}
	
	@Test
	public void getAllUsersTest() throws UserAlreadyExistsException {
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
		
		FindUser findUser = new FindUser(userRepo);
		List<User> users = findUser.getAllUsers();
		
		assertTrue(users.size() == 2);
	}
}

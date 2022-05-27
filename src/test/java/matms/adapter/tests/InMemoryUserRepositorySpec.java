package matms.adapter.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import matms.adapters.InMemoryUserRepository;
import matms.domain.Permission;
import matms.domain.entities.User;
import matms.domain.repositories.UserRepository;
import matms.domain.valueobjects.Id;

public class InMemoryUserRepositorySpec {

	UserRepository userRepo = new InMemoryUserRepository();

	@Test
	public void getByIdTest() {
		Id id = new Id(UUID.randomUUID().toString());
		User user = User.builder()
				.id(id)
				.username("test")
				.password("password")
				.firstName("a")
				.lastName("b")
				.permission(Permission.ORGANIZER)
				.build();
		
		userRepo.addUser(user);
		
		Optional<User> getUser = userRepo.getById(id.getUuid());
		assertTrue(user.equals(getUser.get()));
	}
	
	@Test
	public void getByPermissionsTest() {
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
				.username("iam")
				.password("password")
				.firstName("a")
				.lastName("b")
				.permission(Permission.ORGANIZER)
				.build();
		
		userRepo.addUser(user1);
		userRepo.addUser(user2);
		
		List<User> users = userRepo.getByPermission(Permission.ORGANIZER);
		assertTrue(users.size() == 2);
	}
	
	@Test
	public void getAllUsersTest() {
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
				.username("iam")
				.password("password")
				.firstName("a")
				.lastName("b")
				.permission(Permission.ORGANIZER)
				.build();
		
		userRepo.addUser(user1);
		userRepo.addUser(user2);
		
		List<User> users = userRepo.getAllUsers();
		assertTrue(users.size() == 2);
	}
	
	@Test
	public void deleteUserTest() {
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
				.username("iam")
				.password("password")
				.firstName("a")
				.lastName("b")
				.permission(Permission.ORGANIZER)
				.build();
		
		userRepo.addUser(user1);
		userRepo.addUser(user2);
		
		userRepo.deleteUser(user2);
		List<User> users = userRepo.getAllUsers();
		assertTrue(users.size() == 1);
	}
	
	@Test
	public void updateUserTest() {
		Id id1 = new Id(UUID.randomUUID().toString());
		
		User user1 = User.builder()
				.id(id1)
				.username("test")
				.password("password")
				.firstName("a")
				.lastName("b")
				.permission(Permission.ORGANIZER)
				.build();
		User user2 = User.builder()
				.id(id1)
				.username("iam")
				.password("password")
				.firstName("a")
				.lastName("b")
				.permission(Permission.ORGANIZER)
				.build();
		
		userRepo.addUser(user1);
		userRepo.updateUser(user2);
		assertTrue(userRepo.getById(id1.getUuid()).get().getUsername().equals(user2.getUsername()));
	}
	
	@Test
	public void nextIdTest() {
		Id id1 = new Id(UUID.randomUUID().toString());
		Id id2 = userRepo.nextId();
		
		assertFalse(id1.equals(id2));
	}
}

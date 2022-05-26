package matms.application;

import java.util.UUID;

import matms.domain.entities.User;
import matms.domain.exceptions.UserAlreadyExistsException;
import matms.domain.repositories.UserRepository;
import matms.domain.valueobjects.Id;

// Single-Responsibility-Principle: yes
public final class CreateUser {

	private final UserRepository userRepo;
	
	public CreateUser(final UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public void create(final User user) throws UserAlreadyExistsException {
		if (userRepo.getById(user.getId().getUuid()).isPresent()) {
			throw new UserAlreadyExistsException("User with id " + user.getId().getUuid() + " already exists!");
		}
		User userToSave = User.builder()
				.id(new Id(UUID.randomUUID().toString()))
				.username(user.getUsername())
				.password(user.getPassword())
				.lastName(user.getLastName())
				.firstName(user.getFirstName())
				.permission(user.getPermission())
				.build();
		userRepo.addUser(userToSave);
	}
	
}

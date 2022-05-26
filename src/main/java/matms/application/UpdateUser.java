package matms.application;

import matms.domain.entities.User;
import matms.domain.exceptions.UserNotFoundException;
import matms.domain.repositories.UserRepository;

public final class UpdateUser {

	private final UserRepository userRepo;
	
	public UpdateUser(final UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public void updateUser(final User user) throws UserNotFoundException {
		if (userRepo.getById(user.getId().getUuid()).isPresent()) {
			userRepo.updateUser(user);
		} else {
			throw new UserNotFoundException("User " + user.getUsername() + " not found");
		}
	}
	
}

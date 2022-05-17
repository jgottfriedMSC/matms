package matms.application;

import matms.domain.entities.User;
import matms.domain.repositories.UserRepository;

public final class DeleteUser {

	private final UserRepository userRepo;
	
	public DeleteUser(final UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public void deleteUser(final User user) {
		userRepo.deleteUser(user);
	}
	
}

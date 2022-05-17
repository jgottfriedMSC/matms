package matms.application;

import java.util.List;
import java.util.Optional;

import matms.domain.Permission;
import matms.domain.entities.User;
import matms.domain.repositories.UserRepository;

public final class FindUser {

	private final UserRepository userRepo;
	
	public FindUser(final UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public Optional<User> getById(final String id) {
		return userRepo.getById(id);
	}
	
	public List<User> getByPermission(final Permission permission) {
		return userRepo.getByPermission(permission);
	}
	
	public List<User> getAllUsers() {
		return userRepo.getAllUsers();
	}
}

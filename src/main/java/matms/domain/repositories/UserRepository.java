package matms.domain.repositories;

import java.util.List;
import java.util.Optional;

import matms.domain.Permission;
import matms.domain.entities.User;
import matms.domain.valueobjects.Id;

public interface UserRepository {

	Optional<User> getById(String id);
	
	List<User> getByPermission(Permission permission);
	
	List<User> getAllUsers();
	
	void addUser(User user);
		
	void deleteUser(User user);
		
	void updateUser(User user);
	
	Id nextId();
	
}

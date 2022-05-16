package matms.domain.repositories;

import java.util.List;

import matms.domain.Permission;
import matms.domain.User;

public interface UserRepository {

	User getById(String id);
	
	List<User> getByPermission(Permission permission);
	
	List<User> getAllUsers();
	
	void addUser(User user);
		
	void removeUser(User user);
		
	void updateUser(User user);
}

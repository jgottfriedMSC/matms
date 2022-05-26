package matms.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import matms.domain.Permission;
import matms.domain.entities.User;
import matms.domain.repositories.UserRepository;
import matms.domain.valueobjects.Id;

public class InMemoryUserRepository implements UserRepository {

	private final Map<String, User> inMemUser = new HashMap<>();
	
	@Override
	public Optional<User> getById(String id) {
		return Optional.ofNullable(inMemUser.get(id));
	}

	@Override
	public List<User> getByPermission(Permission permission) {
		return inMemUser.values().stream()
				.filter(user -> user.getPermission().equals(permission))
				.collect(Collectors.toList());
	}

	@Override
	public List<User> getAllUsers() {
		return new ArrayList<>(inMemUser.values());
	}

	@Override
	public void addUser(User user) {
		inMemUser.put(user.getId().getUuid(), user);
	}

	@Override
	public void deleteUser(User user) {
		inMemUser.remove(user.getId().getUuid());		
	}

	@Override
	public void updateUser(User user) {
		inMemUser.put(user.getId().getUuid(), user);
	}

	@Override
	public Id nextId() {
		return new Id(UUID.randomUUID().toString());
	}

}

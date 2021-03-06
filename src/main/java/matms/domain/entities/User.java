package matms.domain.entities;

import matms.domain.Permission;
import matms.domain.valueobjects.Id;

public class User {
	
	private Id id;
	private String username;
	private String password;
	private String lastName;
	private String firstName;
	private Permission permission;
	
	private User(final Id id, final String username, final String password, final String lastName, final String firstName, final Permission permission) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.permission = permission;
	}
	
	public static UserBuilder builder() {
		return new UserBuilder();
	}
	
	public static class UserBuilder {
		private Id id;
		private String username;
		private String password;
		private String lastName;
		private String firstName;
		private Permission permission;
		
		UserBuilder() {
			
		}
		
		public UserBuilder id(final Id id) {
			this.id = id;
			return this;
		}
		
		public UserBuilder username(final String username) {
			this.username = username;
			return this;
		}
		
		public UserBuilder password(final String password) {
			this.password = password;
			return this;
		}
		
		public UserBuilder lastName(final String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public UserBuilder firstName(final String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public UserBuilder permission(final Permission permission) {
			this.permission = permission;
			return this;
		}
		
		public User build() {
			return new User(id, username, password, lastName, firstName, permission);
		}
	}
	
	public Id getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public Permission getPermission() {
		return permission;
	}
	
	@Override
	public String toString() {
		return "User{" +
				"id='" + id.getUuid().toString() + "'" +
				", username='" + username + "'" +
				", password='" + password + "'" +
				", lastName='" + lastName + "'" +
				", firstName='" + firstName + "'" +
				", permission='" + permission.name() + "'}";
				
	}
	
}

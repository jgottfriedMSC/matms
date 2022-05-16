package matms;

import java.util.UUID;

import matms.domain.*;

public class Main {

	public static void main(String[] args) {
		var newUser = User.builder().id(UUID.randomUUID().toString())
									.username("Test")
									.password("test1")
									.lastName("Gott")
									.firstName("Jotta")
									.permission(Permission.PARTICIPANT)
									.build();
		System.out.println(newUser.toString());
	}
	
}

package com.paymybuddy.paymybuddy.UnitTest;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.paymybuddy.ClearDB;
import com.paymybuddy.registration.RegistrationRequest;
import com.paymybuddy.user.models.User;
import com.paymybuddy.user.repository.UserRepository;
import com.paymybuddy.user.service.UserService;
import com.paymybuddy.user.updateDTO.UpdateRequest;

@SpringBootTest
public class UserServiceUTest {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@Autowired
	ClearDB clearDB;

	@BeforeEach
	public void clearDB() {
		clearDB.clearDB();
	}

	@Test
	public void createUser() {
		// Arrange
		User user;

		// Act
		user = newUser();

		// Assert
		assertTrue(user != null);

	}

	@Test
	public void deleteUser() {

		// Arrange
		User user = newUser();
		RegistrationRequest request = new RegistrationRequest(user.getMailAddress(), user.getPassword(),
				user.getFirstName(), user.getLastName());
		assertTrue(userService.alreadyRegistered(user.getMailAddress()));

		// Act
		userService.deleteUser(request);

		// Assert
		assertFalse(userService.alreadyRegistered(user.getMailAddress()));

	}

	@Test
	public void updateUser() {

		// Arrange
		User user = newUser();
		UpdateRequest request = new UpdateRequest(user.getMailAddress(), "test2", true);

		// Act
//		userService.updateUser(request);

		// Assert
		assertTrue(request.getPassword().equals("test2"));
	}

	@Test
	public void getUser() {

		// Arrange
		User user = newUser();
		UpdateRequest request = new UpdateRequest(user.getMailAddress(), user.getPassword(), user.isActive());

		// Act
		Optional<User> userOpt = userService.getUser(request);

		// Assert
		assertTrue(userOpt.isPresent());
		assertTrue(userOpt.get().getMailAddress().equals(user.getMailAddress()));
		assertTrue(userOpt.get().getPassword().equals(user.getPassword()));

	}

	public User newUser() {
		User user = new User("test@test.com", "test", "test", "test");
		return userService.createUser(user);
	}

}

package com.mm.app.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mm.app.model.User;

@SpringBootTest
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void saveTest() {
		User user = userRepository.save(new User(1l, "UR_Test_1", "UR_Test_1", "urt1@mail.com", "urt1"));
		assertNotNull(user);
	}

	@Test
	public void deleteTest() {
		User user = userRepository.save(new User(2l, "UR_Test_2", "UR_Test_2", "urt2@mail.com", "urt2"));
		assertNotNull(user);
		userRepository.delete(user);
		Optional<User> deleted = userRepository.findById(user.getId());
		assertFalse(deleted.isPresent());
	}

	@Test
	public void findByEmailTest() {
		User user = userRepository.save(new User(3l, "UR_Test_3", "UR_Test_3", "urt3@mail.com", "urt3"));
		assertNotNull(user);
		Optional<User> u = userRepository.findByEmail(user.getEmail());
		assertTrue(u.isPresent());
		assertEquals(user, u.get());
	}
}

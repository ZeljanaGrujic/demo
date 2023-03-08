package com.mm.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mm.app.exception.EntityExistsException;
import com.mm.app.exception.InvalidEntityException;
import com.mm.app.model.User;
import com.mm.app.repository.UserRepository;

@SpringBootTest
public class UserServiceTests {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void saveSuccessTest() throws EntityExistsException {
		User user = new User(1l, "US_Test_1", "US_Test_1", "ust1@mail.com", "ust1");
		// when..then
		when(userRepository.save(user)).thenReturn(user);
		User u = userService.save(user);
		assertNotNull(u);
		assertEquals(user, u);
	}

	@Test
	public void saveFailureEmailExistsTest() {
		User user = new User(2l, "US_Test_2", "US_Test_2", "ust2@mail.com", "ust2");
		when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
		assertThrows(EntityExistsException.class, () -> {
			userService.save(user);
		});
	}

	@Test
	public void deleteSuccessTest() throws InvalidEntityException {
		User user = new User(3l, "US_Test_3", "US_Test_3", "ust3@mail.com", "ust3");
		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		userService.deleteById(user.getId());
		verify(userRepository, times(1)).deleteById(user.getId());
	}

	@Test
	public void deleteFailureInvalidUserTest() throws InvalidEntityException {
		User user = new User(4l, "US_Test_4", "US_Test_4", "ust4@mail.com", "ust4");
		when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
		assertThrows(InvalidEntityException.class, () -> {
			userService.deleteById(user.getId());
		});
	}

}

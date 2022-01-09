package org.springframework.samples.minesweeper.user;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.minesweeper.user.User;
import org.springframework.samples.minesweeper.user.UserService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class UserServiceTest {
	@Autowired
	private UserService userService;

	@Test
	void shouldLogicDeleteUser() {
		Boolean isEnabled = true;
		this.userService.logicDeleteUser("player");
		User user = userService.findByUsername("player");
		isEnabled = user.isEnabled();
		assertFalse(isEnabled);
	}
}
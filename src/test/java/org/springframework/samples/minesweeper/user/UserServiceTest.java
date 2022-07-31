package org.springframework.samples.minesweeper.user;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.minesweeper.player.PlayerService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class UserServiceTest {
	@Autowired
	private UserService userService;
	
	@Autowired
	private PlayerService playerService;

	@Test
	void shouldDeleteUser() {
		assertNotNull(this.userService.findByUsername("player"));
		this.playerService.deletePlayer("player"); // delete a Player implies deleting the associated User
		User user = userService.findByUsername("player");
		assertNull(user);
	}
}
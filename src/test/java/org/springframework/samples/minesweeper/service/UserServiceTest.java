package org.springframework.samples.minesweeper.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.minesweeper.player.Player;
import org.springframework.samples.minesweeper.player.PlayerService;
import org.springframework.samples.minesweeper.user.User;
import org.springframework.samples.minesweeper.user.UserService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class UserServiceTest {
	@Autowired
	private UserService userService;
	
	@Test
	void shouldFindPlayersByUserName() {
		Collection<User> players = this.userService.findPlayersByUsername("player1");
		assertThat(players.size()).isEqualTo(1);

		players = this.userService.findPlayersByUsername("player0");
		assertThat(players.isEmpty()).isTrue();
	}
	
	@Test
	void shouldDeletePlayer() {
		Collection<User> players = this.userService.findPlayersByUsername("");
		int size1 = players.size();
		this.userService.deleteUser("player1");
		
		Collection<User> players2 = this.userService.findPlayersByUsername("");
		assertThat(players2.size()).isNotEqualTo(size1);
	}
	
	@Test
	void shouldCreateUser() {
		User u = new User();
		u.setUsername("jose");
		u.setPassword("jose123");
		u.setEnabled(true);
		this.userService.saveUser(u);
		
		User u2 = this.userService.findByUsername("jose");
		assertThat(u.getUsername()).isEqualTo(u2.getUsername());
	}
	
	@Test
	void shouldUpdateUser() {
		User u = this.userService.findByUsername("player1");
		String pass = u.getPassword();
		String newPass = pass + "321";
		u.setPassword(newPass);
		this.userService.saveUser(u);
		
		User u2 = this.userService.findByUsername("player1");
		assertThat(u2.getPassword()).isEqualTo(newPass);
	}
}
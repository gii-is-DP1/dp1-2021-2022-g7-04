package org.springframework.samples.buscaminas.service;



import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.buscaminas.player.Player;
import org.springframework.samples.buscaminas.player.PlayerService;
import org.springframework.samples.buscaminas.user.User;
import org.springframework.samples.buscaminas.user.UserService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class UserServiceTest {
	@Autowired
	private UserService userService;
	
	@Test
	void shouldFindPlayersByUserName() {
		Collection<User> jugadores = this.userService.findPlayersByUsername("jugador1");
		assertThat(jugadores.size()).isEqualTo(1);
		

		jugadores = this.userService.findPlayersByUsername("jugador0");
		assertThat(jugadores.isEmpty()).isTrue();
	}
	
	@Test
	void shouldDeletePlayer() {
		Collection<User> jugadores = this.userService.findPlayersByUsername("");
		int size1 = jugadores.size();
		this.userService.deleteUser("jugador1");
		
		Collection<User> jugadores2 = this.userService.findPlayersByUsername("");
		assertThat(jugadores2.size()).isNotEqualTo(size1);
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
		User u = this.userService.findByUsername("jugador1");
		String pass = u.getPassword();
		String newPass = pass + "321";
		u.setPassword(newPass);
		this.userService.saveUser(u);
		
		User u2 = this.userService.findByUsername("jugador1");
		assertThat(u2.getPassword()).isEqualTo(newPass);
	}

}

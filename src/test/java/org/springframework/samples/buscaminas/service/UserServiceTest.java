package org.springframework.samples.buscaminas.service;



import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.buscaminas.jugador.Jugador;
import org.springframework.samples.buscaminas.jugador.JugadorService;
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

}

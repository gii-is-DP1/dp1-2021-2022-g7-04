package org.springframework.samples.buscaminas.service;



import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.buscaminas.jugador.Jugador;
import org.springframework.samples.buscaminas.jugador.JugadorService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class JugadorServiceTest {
	@Autowired
	private JugadorService jugadorService;
	
	@Test
	void shouldFindJugadoresByName() {
		Collection<Jugador> jugadores = this.jugadorService.findJugadorByName("jugador1");
		assertThat(jugadores.size()).isEqualTo(2);
		

		jugadores = this.jugadorService.findJugadorByName("jugador0");
		assertThat(jugadores.isEmpty()).isTrue();
	}

}

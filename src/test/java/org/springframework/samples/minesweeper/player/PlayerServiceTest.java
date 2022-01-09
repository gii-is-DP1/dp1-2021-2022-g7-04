package org.springframework.samples.minesweeper.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Collection;

import javax.transaction.Transactional;

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
public class PlayerServiceTest {
	@Autowired
	private UserService userService;
	@Autowired
	private PlayerService playerService;

	@Test
	void shouldFindPlayersByUserName() {
		Collection<Player> players = this.playerService.findPlayers("Nombre");
		assertThat(players.size()).isEqualTo(1);

		players = this.playerService.findPlayers("player0");
		assertThat(players.isEmpty()).isTrue();
	}

	@Test
	void shouldCreatePlayer() {
		Collection<Player> players = this.playerService.findPlayers("jose");
		int found = players.size();

		Player p = new Player();
		p.setFirstName("jose");
		p.setLastName("palotes");
		p.setCity("Sevilla");
		p.setAddress("Calle Imagen");
		p.setEmail("jose@gmail.com");
		p.setTelephone("1234567");
		User user = new User();
		user.setUsername("jose1234");
		user.setPassword("1234");
		user.setEnabled(true);
		p.setUser(user);

		this.playerService.savePlayer(p);
		assertThat(p.getId().longValue()).isNotEqualTo(0);

		players = this.playerService.findPlayers("jose");
		assertThat(players.size()).isEqualTo(found + 1);
	}

	@Test
	@Transactional
	void shouldUpdatePlayer() {
		Player player = this.playerService.findPlayerById(6);
		String oldLastName = player.getLastName();
		String newLastName = oldLastName + "X";

		player.setLastName(newLastName);
		this.playerService.savePlayer(player);

		// retrieving new name from database
		player = this.playerService.findPlayerById(6);
		assertThat(player.getLastName()).isEqualTo(newLastName);
	}
}
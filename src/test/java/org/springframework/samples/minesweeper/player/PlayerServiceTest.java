package org.springframework.samples.minesweeper.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Collection;

import javax.transaction.Transactional;

import org.hibernate.criterion.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

	//@Test
	//void shouldFindPlayersByUserName() {
		//Sort sort=Sort.by(Sort.Direction.DESC,"firstName");
		//Pageable pageable=PageRequest.of(0, 5,sort);
		//Collection<Player> players = this.playerService.findPlayers("Nombre",pageable);
		//assertThat(players.size()).isEqualTo(1);

//		players = this.playerService.findPlayers("player0",pageable);
	//	assertThat(players.isEmpty()).isTrue();
	//}

/*	@Test
	void shouldCreatePlayer() {
		Sort sort=Sort.by(Sort.Direction.DESC,"firstName");
		Pageable pageable=PageRequest.of(0, 5,sort);
		Collection<Player> players = this.playerService.findPlayers("jose",pageable);
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

		players = this.playerService.findPlayers("jose",pageable);
		assertThat(players.size()).isEqualTo(found + 1);
	}*/

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
package org.springframework.samples.minesweeper.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.samples.minesweeper.player.Player;
import org.springframework.samples.minesweeper.player.PlayerService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class UserServiceTest {
	@Autowired
	private UserService userService;
	
	@Autowired
	private PlayerService playerService;
	
	@Test
	void shouldCreateUser() {
		Sort sort = Sort.by(Sort.Direction.DESC, "firstName");
		Pageable pageable = PageRequest.of(0, 5, sort);
		Collection<Player> players = this.playerService.findPlayers("jose", 0, pageable);
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
		p.setUser(user);

		this.playerService.savePlayer(p);
		this.userService.saveUser(user);
		assertThat(p.getId().longValue()).isNotEqualTo(0);
		
		Optional<User> userFound; 
		userFound = this.userService.findUser("jose1234");
		assertNotNull(userFound);
		players = this.playerService.findPlayers("jose", 0, pageable);
		assertThat(players.size()).isEqualTo(found + 1);
	}
	
	@Test
	@Transactional
	void shouldSaveUser() {
		
		List<Player> list1 = this.playerService.findAll();
		int size1 = list1.size();
		
		Player player = new Player();
		player.setFirstName("jose");
		player.setLastName("palotes");
		player.setCity("Sevilla");
		player.setAddress("Calle Imagen");
		player.setEmail("jose@gmail.com");
		player.setTelephone("1234567");
		
		User user = new User();
		user.setUsername("jose1234");
		user.setPassword("1234");
		user.setEnabled(true);
		player.setUser(user);
		
		this.playerService.savePlayer(player);
		
		List<Player> list2 = this.playerService.findAll();
		int size2 = list2.size();
		
		assertThat(size2>size1);
	}
	
	@Test
	@Transactional
	void shouldFindUserByUsername() {
		User user = new User();
		user.setUsername("jose1234");
		user.setPassword("1234");
		user.setEnabled(true);
		this.userService.saveUser(user);
		
		Optional<User> user1 = this.userService.findUser("jose1234");

		assertNotNull(user1);
		assertEquals(user.getUsername(), user1.get().getUsername()); 	
		// the username is the id attribute for users, so the same username means the same user
	}
	
	@Test
	void shouldDeleteUser() {
		assertNotNull(this.userService.findUser("player"));
		this.playerService.deletePlayer("player"); // delete a Player implies deleting the associated User
		Optional<User> user = userService.findUser("player");
		assertThat(user.isEmpty());
	}
}
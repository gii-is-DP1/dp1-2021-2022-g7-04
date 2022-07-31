package org.springframework.samples.minesweeper.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	void shouldFindPlayersByFirstName() {
		Sort sort=Sort.by(Sort.Direction.DESC,"firstName");
		Pageable pageable=PageRequest.of(0, 5,sort);
		Collection<Player> players = this.playerService.findPlayers("Nombre",0,pageable);
		assertThat(players.size()).isEqualTo(5);

		players = this.playerService.findPlayers("player0",0,pageable);
		assertThat(players.isEmpty()).isTrue();
	}

	@Test
	void shouldCreatePlayer() {
		Sort sort=Sort.by(Sort.Direction.DESC,"firstName");
		Pageable pageable=PageRequest.of(0, 5,sort);
		Collection<Player> players = this.playerService.findPlayers("jose",0,pageable);
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

		players = this.playerService.findPlayers("jose",0,pageable);
		assertThat(players.size()).isEqualTo(found + 1);
	}

	
	@Test
	@Transactional
	void shouldFindAllPlayers() {
		List<Player> list1 = this.playerService.findAll();
		int size1 = list1.size();
		
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
		
		this.userService.saveUser(user);
		
		List<Player> list2 = this.playerService.findAll();
		int size2 = list2.size();
		
		
		assertThat(size1<size2);
		
	}
	
	@Test
	@Transactional
	void shouldSavePlayer() {
		
		List<Player> list1 = this.playerService.findAll();
		int size1 = list1.size();
		
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
		
		this.userService.saveUser(user);
		
		List<Player> list2 = this.playerService.findAll();
		int size2 = list2.size();
		
		assertThat(size2>size1);
		
	}
	
	@Test
	@Transactional
	void shouldFindPlayersByFirstname() {
		Sort sort=Sort.by(Sort.Direction.DESC,"firstName");
		Pageable pageable=PageRequest.of(0, 5,sort);
		Collection<Player> players = this.playerService.findPlayers("Nombre",0,pageable);
		System.out.println("=================================="+players.size()+"=============================================0");
		assertThat(players.size()).isEqualTo(5);

		players = this.playerService.findPlayers("player0",0,pageable);
		assertThat(players.isEmpty()).isTrue();
		
	}
	
	@Test
	@Transactional
	void shouldFindPlayerById() {
		List<Player> list = this.playerService.findAll();
		Player p1 = list.get(0);
		
		Player p2 = this.playerService.findPlayerById(p1.getId()).get();
		
		
		assertThat(p1.getUser().equals(p2.getUser()));
		
	}
	

	@Test
	@Transactional
	void shouldFindPlayerByUsername() {
		Player p = this.playerService.findPlayerByUsername("luis");
		String city = p.getCity();
		
		assertThat(city.equals("Seville"));
	}
	
	
	@Test
	@Transactional
	void shouldUpdatePlayer() {
		Player player = this.playerService.findPlayerById(6).get();
		String oldLastName = player.getLastName();
		String newLastName = oldLastName + "X";

		player.setLastName(newLastName);
		this.playerService.savePlayer(player);

		// retrieving new name from database
		player = this.playerService.findPlayerById(6).get();
		assertThat(player.getLastName()).isEqualTo(newLastName);
	}
	
	@Test
	void shouldDeletePlayer() {
		assertNotNull(this.playerService.findPlayerByUsername("player"));
		this.playerService.deletePlayer("player");
		Player player = playerService.findPlayerByUsername("player");
		assertNull(player);
	}
}
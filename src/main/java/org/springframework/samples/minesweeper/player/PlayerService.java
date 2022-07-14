package org.springframework.samples.minesweeper.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.minesweeper.user.AuthoritiesService;
import org.springframework.samples.minesweeper.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerService {
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	@Transactional
	public List<Player> findAll() {
		List<Player> allPlayers = new ArrayList<Player>();
		playerRepository.findAll().forEach(allPlayers::add);
		return allPlayers;
	}

	@Transactional(readOnly = true)
	public Optional<Player> findPlayerById(int id) throws DataAccessException {
		return playerRepository.findById(id);
	}

	@Transactional
	public void savePlayer(Player player) throws DataAccessException {
		playerRepository.save(player);
		userService.saveUser(player.getUser());
		authoritiesService.saveAuthorities(player.getUser().getUsername(), "player");
	}

	@Transactional(readOnly = true)
	public List<Player> findPlayers(String firstName,Integer page,Pageable pageable) throws DataAccessException {
		return playerRepository.findPlayers(firstName,pageable);
	}

	@Transactional(readOnly = true)
	public Player findPlayerByUsername(String username) throws DataAccessException {
		return playerRepository.findPlayerByUsername(username);
	}
}
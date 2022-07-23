package org.springframework.samples.minesweeper.player;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
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
		return playerRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Player findPlayerById(int id) throws DataAccessException {
		return playerRepository.findById(id);
	}
	
	@Transactional
	public Player checkPlayerSearched(Player player) throws DataAccessException {
		if (player.getFirstName() == null)
			player.setFirstName(""); // empty string signifies broadest possible search
		return player;
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
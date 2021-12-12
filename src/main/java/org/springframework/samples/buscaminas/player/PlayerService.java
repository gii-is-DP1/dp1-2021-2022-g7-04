package org.springframework.samples.buscaminas.player;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerService {
	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

//	@Transactional
//  public int playerCount() {
//  return (int) playerRepository.count();
//  }

	@Transactional
	public Iterable<Player> findAll() {
		return playerRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Player findPlayerById(int id) throws DataAccessException {
		return playerRepository.findById(id);
	}

	@Transactional
	public void saveplayer(Player player) throws DataAccessException {
		playerRepository.save(player);
	}
	
	@Transactional(readOnly = true)
	public Collection<Player> findPlayers(String username) throws DataAccessException {
		return playerRepository.findPlayers(username);
	}
	
	@Transactional(readOnly = true)
	public Player findPlayerByUsername(String username) throws DataAccessException {
		return playerRepository.findPlayerByUsername(username);
	}
}
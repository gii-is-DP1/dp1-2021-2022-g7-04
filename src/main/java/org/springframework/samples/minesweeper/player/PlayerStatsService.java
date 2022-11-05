package org.springframework.samples.minesweeper.player;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerStatsService {
	
	@Autowired
	private PlayerStatsRepository playerStatsRepository;
	
	@Autowired
	public PlayerStatsService(PlayerStatsRepository playerStatsRepository) {
		this.playerStatsRepository = playerStatsRepository;
	}
	
	@Transactional
	public List<PlayerStats> findAll() throws DataAccessException {
		return (List<PlayerStats>) playerStatsRepository.findAll();
	}

	@Transactional
	public void savePlayerStats(PlayerStats playerStats) throws DataAccessException {
		// creating playerStats
		playerStatsRepository.save(playerStats);
	}

	@Transactional
	public PlayerStats getPlayerStats(String player) throws DataAccessException {
		return playerStatsRepository.getPlayerStats(player);
	}
}

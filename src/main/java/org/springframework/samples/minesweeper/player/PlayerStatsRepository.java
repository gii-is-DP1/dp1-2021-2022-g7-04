package org.springframework.samples.minesweeper.player;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface PlayerStatsRepository extends Repository<PlayerStats, Integer> {

	void save(PlayerStats playerStats) throws DataAccessException;
	
	@Query("SELECT stats FROM PlayerStats stats")
	public List<PlayerStats> findAll();
	
	@Query("SELECT stats FROM PlayerStats stats WHERE stats.player = :player")
	public PlayerStats getPlayerStats(String player);
	
}
package org.springframework.samples.minesweeper.player;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PlayerStatsRepository extends CrudRepository<PlayerStats, Integer> {
	
	@Query("SELECT stats FROM PlayerStats stats WHERE stats.player = :player")
	public PlayerStats getPlayerStats(String player);
	
}
package org.springframework.samples.minesweeper.configuration;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface AdminStatsRepository extends Repository<AdminStats, Integer> {

	void save(AdminStats adminStats) throws DataAccessException;
	
	@Query("SELECT stats FROM AdminStats stats")
	public List<AdminStats> findAll();
	
	@Query("SELECT stats FROM AdminStats stats WHERE stats.levelAchievement =:level")
	public AdminStats getStatsByLevel(String level);
	
	@Query("SELECT stats.games FROM AdminStats stats WHERE stats.levelAchievement =:level")
	public Integer getMinimumGamesByLevel(String level);
	
}
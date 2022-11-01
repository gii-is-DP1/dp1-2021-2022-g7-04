package org.springframework.samples.minesweeper.configuration;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AchievementsRepository extends CrudRepository<Achievements, Integer> {

	
	@Query("SELECT stats FROM Achievements stats WHERE stats.levelAchievement =:level")
	public Achievements getStatsByLevel(String level);
	
	@Query("SELECT stats.games FROM Achievements stats WHERE stats.levelAchievement =:level")
	public Integer getMinimumGamesByLevel(String level);
	
}
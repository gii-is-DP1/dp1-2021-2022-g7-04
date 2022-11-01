package org.springframework.samples.minesweeper.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AchievementsService {
	@Autowired
	private AchievementsRepository achievementsRepository;
	
	@Transactional
	public List<Achievements> findAll() throws DataAccessException {
		return (List<Achievements>) achievementsRepository.findAll();
	}

	@Transactional
	public void saveAchievements(Achievements adminStats) throws DataAccessException {
		// creating adminStats
		achievementsRepository.save(adminStats);
	}

	@Transactional
	public Integer getMinimumGamesByLevel(String levelAchievement) throws DataAccessException {
		return achievementsRepository.getMinimumGamesByLevel(levelAchievement);
	}
	
	@Transactional
	public Achievements getStatsByLevel(String levelAchievement) throws DataAccessException {
		return achievementsRepository.getStatsByLevel(levelAchievement);
	}
}

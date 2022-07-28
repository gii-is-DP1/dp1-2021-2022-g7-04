package org.springframework.samples.minesweeper.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminStatsService {
	@Autowired
	private AdminStatsRepository adminStatsRepository;
	
	@Transactional
	public List<AdminStats> findAll() throws DataAccessException {
		return adminStatsRepository.findAll();
	}

	@Transactional
	public void saveAdminStats(AdminStats adminStats) throws DataAccessException {
		// creating adminStats
		adminStatsRepository.save(adminStats);
	}

	@Transactional
	public Integer getMinimumGamesByLevel(String levelAchievement) throws DataAccessException {
		return adminStatsRepository.getMinimumGamesByLevel(levelAchievement);
	}
	
	@Transactional
	public AdminStats getStatsByLevel(String levelAchievement) throws DataAccessException {
		return adminStatsRepository.getStatsByLevel(levelAchievement);
	}
}

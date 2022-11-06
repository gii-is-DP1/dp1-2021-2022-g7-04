package org.springframework.samples.minesweeper.audit;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditService {
	@Autowired
	private AuditRepository auditRepository;

	@Transactional
	public List<Audit> findAll() throws DataAccessException {
		return (List<Audit>) auditRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Audit> findAudits(Integer page,Pageable pageable) throws DataAccessException {
		return auditRepository.findAudits(pageable);
	}

	@Transactional(readOnly = true)
	public Optional<Audit> findAuditById(int id) throws DataAccessException {
		return auditRepository.findById(id);
	}

	@Transactional
	public void saveAudit(Audit audit) throws DataAccessException {
		// creating audit
		auditRepository.save(audit);
	}

	@Transactional
	public Audit findByActiveBoard(int boardId) throws DataAccessException {
		return auditRepository.findByActiveBoard(boardId);
	}
	
	@Transactional
	public List<Audit> findAllNotCancelledOrStarted() throws DataAccessException {
		return auditRepository.findAllNotCancelledOrStarted();
	}
	
	@Transactional
	public List<Audit> getAllWonGames(String player) throws DataAccessException {
		return auditRepository.getAllWonGames(player);
	}
	
	@Transactional
	public List<Object[]> getHallOfFame() throws DataAccessException {
		return auditRepository.getHallOfFame();
	}
	
	@Transactional
	public List<Double> getNumberGamesByPlayer() throws DataAccessException {
		return auditRepository.getNumberGamesByPlayer();
	}

	@Transactional
	public List<Audit> findPlayerNotCancelledOrStarted(String player) {
		return auditRepository.findPlayerNotCancelledOrStarted(player);
	}
}
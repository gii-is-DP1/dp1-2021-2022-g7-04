package org.springframework.samples.minesweeper.audit;

import java.util.Collection;
import java.util.List;

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
	public Collection<Audit> findAll() throws DataAccessException {
		return auditRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Audit> findAudits(Integer page,Pageable pageable) throws DataAccessException {
		return auditRepository.findAudits(pageable);
	}

	@Transactional(readOnly = true)
	public Audit findAuditById(int id) throws DataAccessException {
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
	public List<Object[]> getHallOfFame() throws DataAccessException {
		return auditRepository.getHallOfFame();
	}
	
	@Transactional
	public List<Double> getNumberGamesByPlayer() throws DataAccessException {
		return auditRepository.getNumberGamesByPlayer();
	}
}
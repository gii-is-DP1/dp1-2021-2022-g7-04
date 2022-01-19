package org.springframework.samples.minesweeper.audit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
}
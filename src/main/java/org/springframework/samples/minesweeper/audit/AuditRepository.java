package org.springframework.samples.minesweeper.audit;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface AuditRepository extends Repository<Audit, Integer> {

	void save(Audit audit) throws DataAccessException;

	@Query("SELECT audit FROM Audit audit WHERE audit.id =:id")
	public Audit findById(@Param("id") int id);

	@Query("SELECT audit FROM Audit audit WHERE audit.minesweeperBoardId = :id")
	public Audit findByActiveBoard(@Param("id") int id);
}
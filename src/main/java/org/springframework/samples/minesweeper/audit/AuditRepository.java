package org.springframework.samples.minesweeper.audit;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AuditRepository extends CrudRepository<Audit, Integer> {

	@Query("SELECT audit FROM Audit audit WHERE audit.minesweeperBoardId = :id")
	public Audit findByActiveBoard(@Param("id") int id);
}
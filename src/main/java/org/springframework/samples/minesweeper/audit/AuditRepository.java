package org.springframework.samples.minesweeper.audit;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface AuditRepository extends Repository<Audit, Integer> {

	void save(Audit audit) throws DataAccessException;
	
	@Query("SELECT audit FROM Audit audit")
	public Collection<Audit> findAll();

	@Query("SELECT audit FROM Audit audit WHERE audit.id =:id")
	public Audit findById(@Param("id") int id);
	
	@Query("SELECT audit FROM Audit audit")
	public List<Audit> findAudits(Pageable pageable);

	@Query("SELECT audit FROM Audit audit WHERE audit.gameStatus='STARTED' AND audit.minesweeperBoardId = :id")
	public Audit findByActiveBoard(@Param("id") int id);
	
	@Query("SELECT audit FROM Audit audit WHERE audit.gameStatus<>'CANCELLED' AND audit.gameStatus<>'STARTED'")
	public List<Audit> findAllNotCancelledOrStarted();
	
	@Query("SELECT a.player, count(a) FROM Audit a WHERE a.gameStatus='WON' GROUP BY a.player ORDER BY count(a) DESC")
	public List<Object[]> getHallOfFame();
	
	@Query("SELECT count(a) FROM Audit a WHERE a.gameStatus<>'CANCELLED' AND a.gameStatus<>'STARTED' GROUP BY a.player")
	public List<Double> getNumberGamesByPlayer();
}
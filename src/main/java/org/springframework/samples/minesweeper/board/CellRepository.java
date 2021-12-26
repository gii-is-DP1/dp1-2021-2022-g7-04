package org.springframework.samples.minesweeper.board;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface CellRepository extends Repository<Cell, Integer> {

	void save(Cell cell) throws DataAccessException;


	@Query("SELECT cell FROM Cell cell WHERE cell.id =:id")
	public Cell findById(@Param("id") int id);
	
	
	@Query("SELECT cell FROM Cell cell WHERE cell.xPosition = :xPosition AND cell.yPosition = :yPosition")
	public Cell findByPosition(@Param("xPosition") int xPosition, @Param("yPosition") int yPosition);
	
}
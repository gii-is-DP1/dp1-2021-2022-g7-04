package org.springframework.samples.minesweeper.board;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CellRepository extends CrudRepository<Cell, Integer> {

	@Query("SELECT cell FROM Cell cell WHERE cell.xPosition = :xPosition AND cell.yPosition = :yPosition")
	public Cell findByPosition(@Param("xPosition") int xPosition, @Param("yPosition") int yPosition);

	@Query("SELECT count(cell) FROM Cell cell WHERE (cell.type = 'MINE' or cell.type = 'MINE-PRESSED') and minesweeperBoard.id = :id")
	public int findAnyMine(@Param("id") int id);
}
package org.springframework.samples.minesweeper.board;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface MinesweeperBoardRepository extends  CrudRepository<MinesweeperBoard, Integer>{
	
	@Query("SELECT board FROM MinesweeperBoard board WHERE playerName=:playerName ORDER BY id DESC")
	public MinesweeperBoard findByPlayer(String playerName);

}

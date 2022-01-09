package org.springframework.samples.minesweeper.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BoardRequestRepository extends CrudRepository<BoardRequest, Integer> {

	@Query("SELECT request FROM BoardRequest request WHERE playerName=:playerName ORDER BY id DESC")
	public BoardRequest findByPlayer(String playerName);
}

package org.springframework.samples.minesweeper.player; 

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PlayerRepository extends CrudRepository<Player, Integer> {

	@Query("SELECT player FROM Player player WHERE player.firstName LIKE :firstName%")
	public List<Player> findPlayers(@Param("firstName") String firstName,Pageable pageable);

	@Query("SELECT player FROM Player player WHERE player.user.username LIKE :username%")
	public Player findPlayerByUsername(@Param("username") String username);
}
package org.springframework.samples.minesweeper.player; 

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface PlayerRepository extends Repository<Player, Integer> {

	@Query("SELECT player FROM Player player WHERE player.id =:id")
	public Player findById(@Param("id") int id);

	void save(Player player) throws DataAccessException;

	@Query("SELECT player FROM Player player")
	List<Player> findAll();
	
	@Query("SELECT player FROM Player player WHERE player.firstName LIKE :firstName%")
	public List<Player> findPlayers(@Param("firstName") String firstName,Pageable pageable);

	@Query("SELECT player FROM Player player WHERE player.user.username LIKE :username%")
	public Player findPlayerByUsername(@Param("username") String username);
}
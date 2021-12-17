package org.springframework.samples.minesweeper.player;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface PlayerRepository extends Repository<Player, Integer> {

	@Query("SELECT player FROM Player player WHERE player.id =:id")
	public Player findById(@Param("id") int id);

	void save(Player player) throws DataAccessException;

	
	@Query("SELECT player FROM Player player WHERE player.user.enabled LIKE true")
	Iterable<Player> findAll();

	@Query("SELECT player FROM Player player WHERE player.firstName LIKE :firstName%")
	public Collection<Player> findPlayers(@Param("firstName") String firstName);
	
	@Query("SELECT player FROM Player player WHERE player.user.username LIKE :username%")
	public Player findPlayerByUsername(@Param("username") String username);

	@Query("UPDATE player SET player.user.enabled=false  WHERE player.id=:id")
	public Player deletePlayer(@Param("id") int id) throws DataAccessException;
	
}

package org.springframework.samples.buscaminas.player;

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

	@Query("SELECT player FROM Player player")
	Iterable<Player> findAll();

	@Query("SELECT player FROM Player player WHERE player.username LIKE :username%")
	public Collection<Player> findPlayers(@Param("username") String username);
	
	@Query("SELECT player FROM Player player WHERE player.username LIKE :username%")
	public Player findPlayerByUsername(@Param("username") String username);
}

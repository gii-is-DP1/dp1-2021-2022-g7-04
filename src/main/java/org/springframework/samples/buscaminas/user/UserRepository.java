package org.springframework.samples.buscaminas.user;

import java.util.Collection;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



import org.springframework.samples.buscaminas.jugador.Jugador;



public interface UserRepository extends CrudRepository<User, String> {

	
	@Query("SELECT user FROM User user WHERE user.username LIKE :username%")
	public User findByUsername(@Param("username") String username);

	@Query("SELECT user FROM User user INNER JOIN Authorities authorities ON user.username = authorities.user WHERE (user.username LIKE :username%) AND (authorities.authority LIKE 'jugador')")
	public Collection<User> findPlayersByUsername(@Param("username") String username);

}

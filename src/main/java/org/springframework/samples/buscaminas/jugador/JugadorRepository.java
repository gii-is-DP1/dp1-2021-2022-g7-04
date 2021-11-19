package org.springframework.samples.buscaminas.jugador;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface JugadorRepository extends Repository<Jugador, Integer> {

	@Query("SELECT jugador FROM Jugador jugador  WHERE jugador.id =:id")
	public Jugador findById(@Param("id") int id);

	void save(Jugador jugador) throws DataAccessException;

	@Query("SELECT jugador FROM Jugador jugador")
	Iterable<Jugador> findAll();

	@Query("SELECT jugador FROM Jugador jugador WHERE jugador.nombre LIKE :nombre%")
	public Collection<Jugador> findByName(@Param("nombre") String nombre);
}

package org.springframework.samples.buscaminas.usuario;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends Repository<Usuario, Integer> {

	@Query("SELECT usuario FROM Usuario usuario  WHERE usuario.id =:id")
	public Usuario findById(@Param("id") int id);

	void save(Usuario usuario) throws DataAccessException;

	@Query("SELECT usuario FROM Usuario usuario")
	Iterable<Usuario> findAll();
}

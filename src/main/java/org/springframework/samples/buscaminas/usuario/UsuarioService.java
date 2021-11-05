package org.springframework.samples.buscaminas.usuario;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepo;

	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepo) {
		this.usuarioRepo = usuarioRepo;
	}

//	@Transactional
//  public int usuarioCount() {
//  return (int) usuarioRepo.count();
//  }

	@Transactional
	public Iterable<Usuario> findAll() {
		return usuarioRepo.findAll();
	}

	@Transactional(readOnly = true)
	public Usuario findUsuarioById(int id) throws DataAccessException {
		return usuarioRepo.findById(id);
	}

	@Transactional
	public void saveUsuario(Usuario usuario) throws DataAccessException {
		usuarioRepo.save(usuario);
	}
}
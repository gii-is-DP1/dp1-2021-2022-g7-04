package org.springframework.samples.minesweeper.player;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JugadorService {
	@Autowired
	private JugadorRepository jugadorRepository;

	@Autowired
	public JugadorService(JugadorRepository jugadorRepository) {
		this.jugadorRepository = jugadorRepository;
	}

//	@Transactional
//  public int jugadorCount() {
//  return (int) jugadorRepository.count();
//  }

	@Transactional
	public Iterable<Jugador> findAll() {
		return jugadorRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Jugador findJugadorById(int id) throws DataAccessException {
		return jugadorRepository.findById(id);
	}

	@Transactional
	public void saveJugador(Jugador jugador) throws DataAccessException {
		jugadorRepository.save(jugador);
	}
	
	@Transactional(readOnly = true)
	public Collection<Jugador> findJugadorByName(String name) throws DataAccessException {
		return jugadorRepository.findByName(name);
	}
}
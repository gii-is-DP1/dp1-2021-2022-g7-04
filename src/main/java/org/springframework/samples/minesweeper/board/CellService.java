package org.springframework.samples.minesweeper.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CellService {
	@Autowired
	private CellRepository cellRepository;

	/*
	 * @Autowired public CellService(CellRepository cellRepository) {
	 * this.cellRepository = cellRepository; }
	 */

	@Transactional(readOnly = true)
	public Cell findCellById(int id) throws DataAccessException {
		return cellRepository.findById(id);
	}

	@Transactional
	public void saveCell(Cell cell) throws DataAccessException {
		// creating cell
		cellRepository.save(cell);
	}

	@Transactional
	public Cell findCellByPosition(int xPosition, int yPosition) throws DataAccessException {
		return cellRepository.findByPosition(xPosition, yPosition);
	}
	
	@Transactional
	public boolean findAnyMine(int boardId) throws DataAccessException {
		return cellRepository.findAnyMine(boardId)>0;
	}
}
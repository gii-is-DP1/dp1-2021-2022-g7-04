package org.springframework.samples.minesweeper.board;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CellService {
	@Autowired
	private CellRepository cellRepository;

	@Transactional(readOnly = true)
	public Optional<Cell> findCellById(int id) throws DataAccessException {
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
		return cellRepository.findAnyMine(boardId) > 0;
	}

	@Transactional
	public void checkMinesAround(Cell cell) throws DataAccessException {
		switch (cell.getMinesAround()) {
		case 1:
			cell.setType("ONE");
			break;
		case 2:
			cell.setType("TWO");
			break;
		case 3:
			cell.setType("THREE");
			break;
		case 4:
			cell.setType("FOUR");
			break;
		case 5:
			cell.setType("FIVE");
			break;
		case 6:
			cell.setType("SIX");
			break;
		case 7:
			cell.setType("SEVEN");
			break;
		case 8:
			cell.setType("HEIGHT");
			break;
		default:
			cell.setType("PRESSED");
		}
		cellRepository.save(cell);
	}
}
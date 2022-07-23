package org.springframework.samples.minesweeper.board;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.minesweeper.model.BoardRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MinesweeperBoardService {
	

	@Autowired
	MinesweeperBoardRepository boardRepo;

	@Autowired
	CellService cellService;

	public Optional<MinesweeperBoard> findById(Integer id) {
		return boardRepo.findById(id);
	}

	public MinesweeperBoard findBoardById(int id) {
		return boardRepo.findBoardById(id);
	}
	
	// Uncover the rest of mines when lost
	public List<Cell> getAllCells(int id) {
		return boardRepo.getAllCells(id);
	}

	// Initialize new game by the numbers of rows and columns
	public Cell[][] initializeGame(BoardRequest boardRequest, MinesweeperBoard board) {
		Cell matrix[][];
		matrix = new Cell[boardRequest.getRows()][boardRequest.getColumns()];
		List<Cell> cells = new ArrayList<Cell>();

		for (int i = 0; i < boardRequest.getRows(); i++) {
			matrix[i] = new Cell[boardRequest.getColumns()];
			for (int j = 0; j < boardRequest.getColumns(); j++) {
				matrix[i][j] = new Cell();
				matrix[i][j].setMinesweeperBoard(board);
				matrix[i][j].setXPosition(i);
				matrix[i][j].setYPosition(j);

				cells.add(matrix[i][j]);
				cellService.saveCell(matrix[i][j]);
			}
		}

		int minas = 0;
		int totalMinas = boardRequest.getMines();
		Cell celdaEx = null;

		while (minas < totalMinas) {
			double valor = Math.floor(Math.random() * cells.size());
			int random = (int) valor;

			if (!cells.get(random).isMine) {
				celdaEx = cells.get(random);
				celdaEx.setMine(true);
				cells.remove(random);
				cells.add(celdaEx);
				cellService.saveCell(celdaEx);
				minas++;
			}
		}
		board.setCells(cells);
		boardRepo.save(board);
		
		log.info(String.format("START GAME - Player: %s, Board: %dx%d, Mines: %d - Level: %s", boardRequest.getPlayerName(),
				boardRequest.getRows(), boardRequest.getColumns(), boardRequest.getMines(), boardRequest.getLevel()));
		
		return matrix;
	}

	// Randomly install all mines in the board of the minesweeper game
	public void randomlyLocaleMines(BoardRequest boardRequest, Cell[][] matrix) {
		int minesPlaced = 0;
		Random random = new Random();
		while (minesPlaced < boardRequest.getMines()) {
			int x = random.nextInt(boardRequest.getRows());
			int y = random.nextInt(boardRequest.getColumns());
			if (!matrix[y][x].isMine()) {
				matrix[y][x].setMine(true);
				minesPlaced++;
			}
		}
	}

	// Locate positions for all mines around
	public void localeMinesArround(BoardRequest boardRequest, Cell matrix[][]) {
		for (int x = 0; x < boardRequest.getRows(); x++) {
			for (int y = 0; y < boardRequest.getColumns(); y++) {
				Cell current = cellService.findCellByPosition(x, y);
				current.setMinesAround(minesNear(matrix, x, y));
				cellService.saveCell(current);
			}
		}
	}

	private int minesNear(Cell[][] matrix, int x, int y) {
		int mines = 0;
		mines += mineAt(matrix, y - 1, x - 1);
		mines += mineAt(matrix, y - 1, x);
		mines += mineAt(matrix, y - 1, x + 1);
		mines += mineAt(matrix, y, x - 1);
		mines += mineAt(matrix, y, x + 1);
		mines += mineAt(matrix, y + 1, x - 1);
		mines += mineAt(matrix, y + 1, x);
		mines += mineAt(matrix, y + 1, x + 1);
		return mines;
	}

	private int mineAt(Cell[][] matrix, int y, int x) {
		if (y >= 0 && y < matrix[0].length && x >= 0 && x < matrix.length && matrix[x][y].isMine()) {
			return 1;
		} else {
			return 0;
		}
	}

	public boolean mineFound(int row, int column) {
		Cell current = this.cellService.findCellByPosition(row, column);
		return current.isMine();
	}

	public boolean alreadyWon(BoardRequest boardRequest) {
		Cell[][] matrix = new Cell[boardRequest.getRows()][boardRequest.getColumns()];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				Cell current = this.cellService.findCellByPosition(i, j);
				if (!current.isMine() && current.getType().equals("UNPRESSED")) {
					return false;
				}
			}
		}
		return true;
	}

	public void clearEmptySpots(int x, int y, int xMax, int yMax) {
		// Base Case
		if (x < 0 || x > xMax || y < 0 || y > yMax) {
			return;
		}
		
		Cell current = cellService.findCellByPosition(x, y);

		if (current.getMinesAround() == 0 && (!current.getType().equals("PRESSED") && !current.isMine())) {
			current.setType("PRESSED");
			cellService.saveCell(current);
			clearEmptySpots(x + 1, y, xMax, yMax);
			clearEmptySpots(x + 1, y + 1, xMax, yMax);
			clearEmptySpots(x + 1, y - 1, xMax, yMax);
			clearEmptySpots(x - 1, y, xMax, yMax);
			clearEmptySpots(x - 1, y - 1, xMax, yMax);
			clearEmptySpots(x - 1, y + 1, xMax, yMax);
			clearEmptySpots(x, y - 1, xMax, yMax);
			clearEmptySpots(x, y + 1, xMax, yMax);
			
		// Set numbers mines around on cells are near from clear cells
		} else if(current.getMinesAround()>0 && (current.getType().equals("UNPRESSED") && !current.isMine())){
			cellService.checkMinesAround(current);
		}else {
			return;
		}
	}

	@Transactional
	public void saveBoard(MinesweeperBoard minesweeperBoard) throws DataAccessException {
		boardRepo.save(minesweeperBoard);
	}

	public boolean existsBoardForPlayer(String playerName) {
		boolean res = false;
		if (this.findByPlayer(playerName) != null)
			res = true;
		return res;
	}

	public MinesweeperBoard findByPlayer(String playerName) {
		return boardRepo.findByPlayer(playerName);
	}

	@Transactional
	public void deleteMinesweeperBoard(MinesweeperBoard board) throws DataAccessException {
		boardRepo.delete(board);
	}

	public Date getFormattedDate() {
		Calendar date = Calendar.getInstance();
		String y = String.valueOf(date.get(Calendar.YEAR));
		String m = String.valueOf(date.get(Calendar.MONTH+1));
		String d = String.valueOf(date.get(Calendar.DAY_OF_MONTH)); 

		String hh = String.valueOf(date.get(Calendar.HOUR_OF_DAY));
		String mm = String.valueOf(date.get(Calendar.MINUTE));
		String ss = String.valueOf(date.get(Calendar.SECOND));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String dateInString = String.format("%s/%s/%s %s:%s:%s",y,m,d,hh,mm,ss);
		Date res = new Date();
		try {
			res = sdf.parse(dateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
}
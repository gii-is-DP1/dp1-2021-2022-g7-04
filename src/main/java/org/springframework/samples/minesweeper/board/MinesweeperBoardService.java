package org.springframework.samples.minesweeper.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.minesweeper.model.BoardRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MinesweeperBoardService {

	@Autowired 
	MinesweeperBoardRepository boardRepo;
	
	@Autowired 
	CellService cellService;
	
	public Optional<MinesweeperBoard> findById(Integer id){
		return boardRepo.findById(id);
	}
	
	// Initialize new game by num of rows and columns
	public Cell[][] initializeGame(BoardRequest boardRequest, MinesweeperBoard board) {
		Cell matrix[][];
		matrix = new Cell[boardRequest.getRows()][];
		List<Cell> cells = new ArrayList<Cell>();
		for (int i=0 ; i < boardRequest.getRows(); i++) {
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
		board.setCells(cells);
		System.out.format("[MinesweeperService] - A new game was initialized with rows=%d, columns=%d, mines=%d for usaername=%s - Level: %s",
			boardRequest.getRows(), boardRequest.getColumns(), boardRequest.getMines(), boardRequest.getPlayerName(), boardRequest.getLevel());
		return matrix;
	}
	
	// Randomly install all mines in the board of the minesweeper game
	public void randomlyLocaleMines(BoardRequest boardRequest, Cell[][] matrix) {
		int minesPlaced = 0;
		Random random = new Random();
		while(minesPlaced < boardRequest.getMines()) {
			int x = random.nextInt(boardRequest.getRows());
			int y = random.nextInt(boardRequest.getColumns());
			if(!matrix[y][x].isMine()) {
				matrix[y][x].setMine(true);
				minesPlaced ++;
			}
		}
		System.out.format("[MinesweeperService] - Already installed mines for game of username=%s", boardRequest.getPlayerName());
	}
	
	// Locate positions for all mines around
	public void localeMinesArround(BoardRequest boardRequest, Cell[][] matrix) {
		for (int x=0; x < boardRequest.getRows(); x ++) {
			for (int y= 0; y < boardRequest.getColumns(); y++) {
				matrix[x][y].setMinesAround(minesNear(matrix, x, y));
			}
		}
	}
	
	private int minesNear(Cell[][] matrix, int x, int y) {
		int mines = 0;
		mines += mineAt(matrix, y - 1, x - 1);
		mines += mineAt(matrix, y - 1, x);
		mines += mineAt(matrix,y - 1, x + 1);
		mines += mineAt(matrix, y,x - 1);
		mines += mineAt(matrix, y, x + 1);
		mines += mineAt(matrix, y + 1, x - 1);
		mines += mineAt(matrix, y + 1, x);
		mines += mineAt(matrix,y + 1, x + 1);
		return mines;
	}
	
	private int mineAt(Cell[][] matrix, int y, int x) {
		if(y >= 0 && y < matrix[0].length && x >= 0 && x < matrix.length && matrix[y][x].isMine()) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public boolean mineFound(Cell[][] matrix, int row, int column) {
		return matrix[row][column].isMine();
	}
	
	public boolean alreadyWon(Cell[][] matrix) {
		for (int i=0 ; i < matrix.length ; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (!matrix[i][j].isMine() && !matrix[i][j].getType().equals("PRESSED")) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void clearEmptySpots(Cell[][] matrix, int x, int y, int xMax, int yMax) {
		// Base Case
		if (x < 0 || x > xMax || y < 0 || y > yMax){
			return;
		}

		if ( matrix[x][y].getMinesAround() == 0 && !matrix[x][y].getType().equals("PRESSED")) {
			matrix[x][y].setType("PRESSED");
			clearEmptySpots(matrix, x+1, y , xMax, yMax);
			clearEmptySpots(matrix, x+1, y+1 , xMax, yMax);
			clearEmptySpots(matrix, x+1, y-1 , xMax, yMax);
			clearEmptySpots(matrix, x-1, y , xMax, yMax);
			clearEmptySpots(matrix, x-1, y-1 , xMax, yMax);
			clearEmptySpots(matrix, x-1, y+1 , xMax, yMax);
			clearEmptySpots(matrix, x, y-1 , xMax, yMax);
			clearEmptySpots(matrix, x, y+1 , xMax, yMax);
		} else {
			return;
		}
	}
	
	@Transactional
	public void saveBoard(MinesweeperBoard minesweeperBoard) throws DataAccessException {
		boardRepo.save(minesweeperBoard);
	}
	
}

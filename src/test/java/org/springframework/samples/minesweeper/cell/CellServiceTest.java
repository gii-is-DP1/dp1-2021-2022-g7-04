package org.springframework.samples.minesweeper.cell;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.minesweeper.board.CellService;
import org.springframework.samples.minesweeper.board.MinesweeperBoard;
import org.springframework.samples.minesweeper.board.MinesweeperBoardService;
import org.springframework.samples.minesweeper.board.Cell;
import org.springframework.samples.minesweeper.player.Player;
import org.springframework.samples.minesweeper.player.PlayerService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CellServiceTest {
	@Autowired
	private CellService cellService;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private MinesweeperBoardService minesweeperBoardService;
	

	@Test
	void shouldfindCellById() {
		Cell c = new Cell();
		this.cellService.saveCell(c);
		
		Cell c2 = this.cellService.findCellById(c.getId()).get();
		
		assertNotNull(c2);
	}
	
	@Test
	void shouldSaveCell() {
		Cell c = new Cell();
		c.setXPosition(2);
		this.cellService.saveCell(c);
		
		Cell c2 = this.cellService.findCellById(c.getId()).get();
		
		assertThat(c2.getXPosition()==2);
	}
	
	@Test
	void shouldFindCellByPosition() {
		Cell c = new Cell();
		c.setXPosition(2);
		c.setYPosition(2);
		this.cellService.saveCell(c);
		
		Cell c2 = this.cellService.findCellByPosition(2, 2);
		
		assertThat(c2.getId()==c.getId());
	}
	
	@Test
	void shouldfindAnyMine() {
		Player p = this.playerService.findPlayerById(6).get();
		MinesweeperBoard board = new MinesweeperBoard(p.getFirstName());
		
		Cell c = new Cell();
		c.setMine(true);
		c.setMinesweeperBoard(board);
		this.cellService.saveCell(c);
		this.minesweeperBoardService.saveBoard(board);
		
		assertThat(this.cellService.findAnyMine(board.getId()));
	}
	
	
	/*
	@Test
	void shouldFindBoardById() {
		Player p = this.playerService.findPlayerById(6);
		MinesweeperBoard board = new MinesweeperBoard(p.getFirstName());
		this.minesweeperBoardService.saveBoard(board);
		int boardId = board.getId();
		MinesweeperBoard board2 = minesweeperBoardService.findBoardById(boardId);
		assertNotNull(board2.getId());
	}

	@Test
	void shouldSaveBoard() {
		Player p = this.playerService.findPlayerById(6);
		MinesweeperBoard board = new MinesweeperBoard(p.getFirstName());
		this.minesweeperBoardService.saveBoard(board);
		assertNotNull(board.getId());
	}

	@Test
	void shouldExistBoardForPlayer() {
		Player p = this.playerService.findPlayerById(6);
		MinesweeperBoard board = new MinesweeperBoard(p.getFirstName());
		this.minesweeperBoardService.saveBoard(board);
		Boolean bol = this.minesweeperBoardService.existsBoardForPlayer(p.getFirstName());
		assertThat(bol);
	}

	@Test
	void shouldFindByPlayer() {
		Player p = this.playerService.findPlayerById(6);
		MinesweeperBoard board = new MinesweeperBoard(p.getFirstName());
		this.minesweeperBoardService.saveBoard(board);
		MinesweeperBoard board2 = this.minesweeperBoardService.findByPlayer(p.getFirstName());
		assertThat(board.getId().equals(board2.getId()));
	}

	@Test
	void shouldDeleteBoard() {
		Player p = this.playerService.findPlayerById(6);
		MinesweeperBoard board = new MinesweeperBoard(p.getFirstName());
		this.minesweeperBoardService.saveBoard(board);
		int boardId = board.getId();
		this.minesweeperBoardService.deleteMinesweeperBoard(board);
		Optional<MinesweeperBoard> board2 = this.minesweeperBoardService.findById(boardId);
		assertThat(!board.equals(board2));
	}
	
	*/
	
}
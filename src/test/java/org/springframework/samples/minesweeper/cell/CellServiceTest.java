package org.springframework.samples.minesweeper.cell;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

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
	@Transactional
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
	
	@Test
	void shouldCheckMinesAround() {
		Cell c = new Cell();
		c.setMinesAround(5);
		
		this.cellService.checkMinesAround(c);
		
		assertThat(c.getType().equals("FIVE"));
	}
	
}
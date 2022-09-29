package org.springframework.samples.minesweeper.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.minesweeper.player.Player;
import org.springframework.samples.minesweeper.player.PlayerService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class MinesweeperBoardServiceTest {
	@Autowired
	private MinesweeperBoardService minesweeperBoardService;
	@Autowired
	private PlayerService playerService;

	@Test
	void shouldFindBoardById() {
		Player p = this.playerService.findPlayerById(6).get();
		MinesweeperBoard board = new MinesweeperBoard(p.getFirstName());
		this.minesweeperBoardService.saveBoard(board);
		int boardId = board.getId();
		MinesweeperBoard board2 = minesweeperBoardService.findById(boardId).get();
		assertNotNull(board2.getId());
	}

	@Test
	@Transactional
	void shouldSaveBoard() {
		Player p = this.playerService.findPlayerById(6).get();
		MinesweeperBoard board = new MinesweeperBoard(p.getFirstName());
		this.minesweeperBoardService.saveBoard(board);
		assertNotNull(board.getId());
	}

	@Test
	void shouldExistBoardForPlayer() {
		Player p = this.playerService.findPlayerById(6).get();
		MinesweeperBoard board = new MinesweeperBoard(p.getFirstName());
		this.minesweeperBoardService.saveBoard(board);
		Boolean bol = this.minesweeperBoardService.existsBoardForPlayer(p.getFirstName());
		assertThat(bol);
	}

	@Test
	void shouldFindByPlayer() {
		Player p = this.playerService.findPlayerById(6).get();
		MinesweeperBoard board = new MinesweeperBoard(p.getFirstName());
		this.minesweeperBoardService.saveBoard(board);
		MinesweeperBoard board2 = this.minesweeperBoardService.findByPlayer(p.getFirstName());
		assertThat(board.getId().equals(board2.getId()));
	}

	@Test
	@Transactional
	void shouldDeleteBoard() {
		Player p = this.playerService.findPlayerById(6).get();
		MinesweeperBoard board = new MinesweeperBoard(p.getFirstName());
		this.minesweeperBoardService.saveBoard(board);
		int boardId = board.getId();
		this.minesweeperBoardService.deleteMinesweeperBoard(board);
		Optional<MinesweeperBoard> board2 = this.minesweeperBoardService.findById(boardId);
		assertThat(!board.equals(board2));
	}
}
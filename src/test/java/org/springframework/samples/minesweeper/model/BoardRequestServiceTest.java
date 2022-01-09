package org.springframework.samples.minesweeper.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.minesweeper.board.DifficultyLevel;
import org.springframework.samples.minesweeper.board.MinesweeperBoard;
import org.springframework.samples.minesweeper.board.MinesweeperBoardService;
import org.springframework.samples.minesweeper.model.BoardRequest;
import org.springframework.samples.minesweeper.model.BoardRequestService;
import org.springframework.samples.minesweeper.player.Player;
import org.springframework.samples.minesweeper.player.PlayerService;
import org.springframework.samples.minesweeper.user.User;
import org.springframework.samples.minesweeper.user.UserService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class BoardRequestServiceTest {
	@Autowired
	private BoardRequestService boardRequestService;
	@Autowired
	private PlayerService playerService;

	@Test
	void shouldExistRequestBoardForPlayer() {
		Player p = this.playerService.findPlayerById(6);
		BoardRequest boardRequest = new BoardRequest(DifficultyLevel.BEGINNER, p.getFirstName());
		this.boardRequestService.saveRequest(boardRequest);
		Boolean bol = this.boardRequestService.existsRequestBoardForPlayer(p.getFirstName());
		assertThat(bol);
	}

	@Test
	void shouldSaveRequestBoard() {
		Player p = this.playerService.findPlayerById(6);
		BoardRequest boardRequest = new BoardRequest(DifficultyLevel.BEGINNER, p.getFirstName());
		this.boardRequestService.saveRequest(boardRequest);
		assertNotNull(boardRequest.getId());
	}

	@Test
	void shouldFindRequestBoardByPlayer() {
		Player p = this.playerService.findPlayerById(6);
		BoardRequest boardRequest = new BoardRequest(DifficultyLevel.BEGINNER, p.getFirstName());
		this.boardRequestService.saveRequest(boardRequest);
		BoardRequest boardRequest2 = this.boardRequestService.findByPlayer(p.getFirstName());
		assertThat(boardRequest.getId().equals(boardRequest2.getId()));
	}

	@Test
	void shouldDeleteBoardRequest() {
		Player p = this.playerService.findPlayerById(6);
		BoardRequest boardRequest = new BoardRequest(DifficultyLevel.BEGINNER, p.getFirstName());
		this.boardRequestService.saveRequest(boardRequest);
		this.boardRequestService.deleteRequest(boardRequest);
		BoardRequest boardRequest2 = this.boardRequestService.findByPlayer(p.getFirstName());
		assertThat(!boardRequest.equals(boardRequest2));
	}
}
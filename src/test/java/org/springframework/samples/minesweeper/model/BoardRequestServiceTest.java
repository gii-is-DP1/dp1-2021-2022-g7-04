package org.springframework.samples.minesweeper.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.minesweeper.board.DifficultyLevel;
import org.springframework.samples.minesweeper.player.Player;
import org.springframework.samples.minesweeper.player.PlayerService;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class BoardRequestServiceTest {
	@Autowired
	private BoardRequestService boardRequestService;
	@Autowired
	private PlayerService playerService;

	@Test
	void shouldExistRequestBoardForPlayer() {
		Player p = this.playerService.findPlayerById(6).get();
		BoardRequest boardRequest = new BoardRequest(DifficultyLevel.BEGINNER, p.getFirstName());
		this.boardRequestService.saveRequest(boardRequest);
		Boolean bol = this.boardRequestService.existsRequestBoardForPlayer(p.getFirstName());
		assertThat(bol);
	}

	@Test
	@Transactional
	void shouldSaveRequestBoard() {
		Player p = this.playerService.findPlayerById(6).get();
		BoardRequest boardRequest = new BoardRequest(DifficultyLevel.BEGINNER, p.getFirstName());
		this.boardRequestService.saveRequest(boardRequest);
		assertNotNull(boardRequest.getId());
	}

	@Test
	void shouldFindRequestBoardByPlayer() {
		Player p = this.playerService.findPlayerById(6).get();
		BoardRequest boardRequest = new BoardRequest(DifficultyLevel.BEGINNER, p.getFirstName());
		this.boardRequestService.saveRequest(boardRequest);
		BoardRequest boardRequest2 = this.boardRequestService.findByPlayer(p.getFirstName());
		assertThat(boardRequest.getId().equals(boardRequest2.getId()));
	}

	@Test
	@Transactional
	void shouldDeleteBoardRequest() {
		Player p = this.playerService.findPlayerById(6).get();
		BoardRequest boardRequest = new BoardRequest(DifficultyLevel.BEGINNER, p.getFirstName());
		this.boardRequestService.saveRequest(boardRequest);
		this.boardRequestService.deleteRequest(boardRequest);
		BoardRequest boardRequest2 = this.boardRequestService.findByPlayer(p.getFirstName());
		assertThat(!boardRequest.equals(boardRequest2));
	}
}
package org.springframework.samples.minesweeper.board;

import java.security.Principal;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.minesweeper.audit.Audit;
import org.springframework.samples.minesweeper.audit.AuditService;
import org.springframework.samples.minesweeper.model.BoardRequest;
import org.springframework.samples.minesweeper.model.BoardRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class GameController {

	@Autowired
	MinesweeperBoardService minesweeperBoardService;

	@Autowired
	private BoardRequestService boardRequestService;
	@Autowired
	private CellService cellService;
	@Autowired
	private AuditService auditService;

	@GetMapping(value = "/selectGame")
	public String selectGame(Map<String, Object> model, BoardRequest boardRequest, HttpServletRequest request) {

		Principal player = request.getUserPrincipal();
		BoardRequest boardRequest2 = boardRequestService.findByPlayer(player.getName());

		if (boardRequest2 != null) {
			model.put("gameStarted", true);
		} else {
			model.put("gameStarted", false);
		}

		return "selectGame";
	}

	@GetMapping(value = "/finishGame")
	public String finishGame(@RequestParam(required = false) boolean winner, Map<String, Object> model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Principal player = request.getUserPrincipal();

		MinesweeperBoard board = this.minesweeperBoardService.findByPlayer(player.getName());
		BoardRequest boardRequest = boardRequestService.findByPlayer(player.getName());
		boolean foundAnyMine = this.cellService.findAnyMine(board.getId());

		this.minesweeperBoardService.deleteMinesweeperBoard(board);
		boardRequestService.deleteRequest(boardRequest);

		// WIN GAME
		if (winner) {
			redirectAttributes.addAttribute("winner", true);

			// End audit game (WIN GAME)
			Date date = this.minesweeperBoardService.getFormattedDate();
			Audit gameAudit = this.auditService.findByActiveBoard(board.getId());
			gameAudit.setEndDate(date);
			gameAudit.setPlayer(player.getName());
			gameAudit.setGameStatus("WON");
			gameAudit.setDifficulty(boardRequest.getLevel().name());
			gameAudit.setFinished(true);
			this.auditService.saveAudit(gameAudit);
			log.info(String.format("GAME OVER - Player '%s' has WON the game!", player.getName()));
		} else if (!foundAnyMine) {
			// End audit game (CANCELLED GAME)
			Date date = this.minesweeperBoardService.getFormattedDate();
			Audit gameAudit = this.auditService.findByActiveBoard(board.getId());
			gameAudit.setEndDate(date);
			gameAudit.setPlayer(player.getName());
			gameAudit.setGameStatus("CANCELLED");
			gameAudit.setDifficulty(boardRequest.getLevel().name());
			gameAudit.setFinished(true);
			this.auditService.saveAudit(gameAudit);
			log.info(String.format("GAME OVER - Player '%s' has cancelled the game", player.getName()));
		}

		return "redirect:/welcome";
	}

	@GetMapping(value = "/newGame")
	public String newGame(@RequestParam(value = "difficulty", required = false) String difficulty,
			Map<String, Object> model, BoardRequest playRequest, HttpServletRequest request) {
		Principal player = request.getUserPrincipal();

		// Manage the board of the player
		int flagsInMines = 0;
		MinesweeperBoard board = null;
		if (!minesweeperBoardService.existsBoardForPlayer(player.getName())) {
			board = new MinesweeperBoard(player.getName());
			minesweeperBoardService.saveBoard(board);

		} else {
			board = minesweeperBoardService.findByPlayer(player.getName());
			for (Cell c : board.getCells()) {
				if (c.getType().equals("FLAG")) {
					flagsInMines--;
				}
			}
		}

		boolean existPlayRequest = false;

		// Manage the play request of the player
		BoardRequest boardRequest = null;
		if (!boardRequestService.existsRequestBoardForPlayer(player.getName())) {
			DifficultyLevel level = DifficultyLevel.BEGINNER;

			if (difficulty.equals("Medium")) {
				level = DifficultyLevel.MEDIUM;
				boardRequest = new BoardRequest(level, player.getName());
				flagsInMines = boardRequest.getMines();
			} else if (difficulty.equals("Ace")) {
				level = DifficultyLevel.ACE;
				boardRequest = new BoardRequest(level, player.getName());
				flagsInMines = boardRequest.getMines();
			} else if (difficulty.equals("Custom")) {
				level = DifficultyLevel.CUSTOM;
				boardRequest = new BoardRequest(playRequest.getRows(), playRequest.getColumns(), playRequest.getMines(),
						player.getName());
				flagsInMines = boardRequest.getMines();
			} else {
				boardRequest = new BoardRequest(level, player.getName());
				flagsInMines = boardRequest.getMines();
			}

			boardRequestService.saveRequest(boardRequest);
		} else {
			boardRequest = boardRequestService.findByPlayer(player.getName());
			existPlayRequest = true;
			flagsInMines = flagsInMines + boardRequest.getMines();
		}

		// Manage the miscellaneous
		boolean foundAnyMine = false;
		if (!existPlayRequest) {

			// Initialize board
			Cell[][] matrixBoard = minesweeperBoardService.initializeGame(boardRequest, board);

			// Locale mines around for all cells
			minesweeperBoardService.localeMinesArround(boardRequest, matrixBoard);

			// Start audit game (STARTED GAME)
			Date date = this.minesweeperBoardService.getFormattedDate();
			Audit gameAudit = new Audit();
			gameAudit.setStartDate(date);
			gameAudit.setPlayer(player.getName());
			gameAudit.setGameStatus("STARTED");
			gameAudit.setDifficulty(boardRequest.getLevel().name());
			gameAudit.setFinished(false);
			gameAudit.setMinesweeperBoardId(board.getId());
			this.auditService.saveAudit(gameAudit);
		} else {
			foundAnyMine = this.cellService.findAnyMine(board.getId());
		}

		// LOSE GAME
		if (foundAnyMine) {
			model.put("loserMessage", "Sorry, you have lost...");

			// End audit game (LOSE GAME)
			Date date = this.minesweeperBoardService.getFormattedDate();
			Audit gameAudit = this.auditService.findByActiveBoard(board.getId());
			gameAudit.setEndDate(date);
			gameAudit.setPlayer(player.getName());
			gameAudit.setGameStatus("LOST");
			gameAudit.setDifficulty(boardRequest.getLevel().name());
			gameAudit.setFinished(true);
			this.auditService.saveAudit(gameAudit);
			log.info(String.format("GAME OVER - Player '%s' has lost the game", player.getName()));
		}

		model.put("flagsInMines", flagsInMines);
		model.put("minesweeperBoard", board);
		model.put("boardRequest", boardRequest);
		model.put("difficulty", difficulty);
		return "newGame";
	}

	@GetMapping(value = "/continueGame")
	public String continueGame(Map<String, Object> model, HttpServletRequest request) {
		Principal player = request.getUserPrincipal();
		int flagsInMines = 0;

		MinesweeperBoard board = this.minesweeperBoardService.findByPlayer(player.getName());
		for (Cell c : board.getCells()) {
			if (c.getType().equals("FLAG")) {
				flagsInMines--;
			}
		}

		BoardRequest boardRequest = boardRequestService.findByPlayer(player.getName());
		flagsInMines = flagsInMines + boardRequest.getMines();

		model.put("flagsInMines", flagsInMines);
		model.put("minesweeperBoard", board);
		model.put("boardRequest", boardRequest);
		return "newGame";
	}

	@GetMapping(value = "/restartGame")
	public String restartGame(Map<String, Object> model, HttpServletRequest request) {
		Principal player = request.getUserPrincipal();

		MinesweeperBoard board = this.minesweeperBoardService.findByPlayer(player.getName());
		BoardRequest boardRequest = boardRequestService.findByPlayer(player.getName());
		boolean foundAnyMine = this.cellService.findAnyMine(board.getId());

		if (!foundAnyMine) {
			// End audit game (CANCELLED GAME)
			Date date = this.minesweeperBoardService.getFormattedDate();
			Audit gameAudit = this.auditService.findByActiveBoard(board.getId());
			gameAudit.setEndDate(date);
			gameAudit.setPlayer(player.getName());
			gameAudit.setGameStatus("CANCELLED");
			gameAudit.setDifficulty(boardRequest.getLevel().name());
			gameAudit.setFinished(true);
			this.auditService.saveAudit(gameAudit);
		}

		this.minesweeperBoardService.deleteMinesweeperBoard(board);
		board = new MinesweeperBoard(player.getName());
		minesweeperBoardService.saveBoard(board);

		// Initialize board
		Cell[][] matrixBoard = minesweeperBoardService.initializeGame(boardRequest, board);

		// Locale mines around for all cells
		minesweeperBoardService.localeMinesArround(boardRequest, matrixBoard);

		// Start audit game (STARTED GAME)
		Date newDate = this.minesweeperBoardService.getFormattedDate();
		Audit newGameAudit = new Audit();
		newGameAudit.setStartDate(newDate);
		newGameAudit.setPlayer(player.getName());
		newGameAudit.setGameStatus("STARTED");
		newGameAudit.setDifficulty(boardRequest.getLevel().name());
		newGameAudit.setFinished(false);
		newGameAudit.setMinesweeperBoardId(board.getId());
		this.auditService.saveAudit(newGameAudit);

		int flagsInMines = boardRequest.getMines();

		model.put("flagsInMines", flagsInMines);
		model.put("minesweeperBoard", board);
		model.put("boardRequest", boardRequest);
		return "newGame";
	}
}
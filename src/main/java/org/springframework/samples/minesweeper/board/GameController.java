package org.springframework.samples.minesweeper.board;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.minesweeper.model.BoardRequest;
import org.springframework.samples.minesweeper.model.BoardRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GameController {

	@Autowired
	MinesweeperBoardService minesweeperService;

	@Autowired
	BoardRequestService boardRequestService;

	@GetMapping(value = "/selectGame")
	public String selectGame(Map<String, Object> model, BoardRequest boardRequest, HttpServletRequest request) {

		Principal player = request.getUserPrincipal();
		BoardRequest boardRequest2 = boardRequestService.findByPlayer(player.getName());

		if (boardRequest2 != null) {
			model.put("gameStarted", true);
		} else {
			model.put("gameStarted", false);
		}
		/*
		 * if(minesweeperService.existsBoardForPlayer(player.getName())) { BoardRequest
		 * request2 = boardRequestService.findByPlayer(player.getName());
		 * boardRequestService.deleteRequest(request2);
		 * 
		 * } else {
		 * 
		 * }
		 */
		return "selectGame";
	}

	@GetMapping(value = "/finishGame")
	public String finishGame(@RequestParam(required=false) String gameStatus,
			Map<String, Object> model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Principal player = request.getUserPrincipal();

		MinesweeperBoard board = this.minesweeperService.findByPlayer(player.getName());
		BoardRequest boardRequest = boardRequestService.findByPlayer(player.getName());

		this.minesweeperService.deleteMinesweeperBoard(board);
		boardRequestService.deleteRequest(boardRequest);
		
		if(gameStatus!="") {
			redirectAttributes.addAttribute("gameStatus", gameStatus);
		}
		return "redirect:/welcome";
	}

	@GetMapping(value = "/newGame")
	public String newGame(@RequestParam(value = "difficulty", required = false) String difficulty,
			Map<String, Object> model, BoardRequest playRequest, HttpServletRequest request) {
		Principal player = request.getUserPrincipal();

		// Manage the board of the player
		MinesweeperBoard board = null;
		if (!minesweeperService.existsBoardForPlayer(player.getName())) {
			board = new MinesweeperBoard(player.getName());

			minesweeperService.saveBoard(board);
		} else {
			board = minesweeperService.findByPlayer(player.getName());
		}

		boolean existPlayRequest = false;

		// Manage the play request of the player
		BoardRequest boardRequest = null;
		if (!boardRequestService.existsRequestBoardForPlayer(player.getName())) {
			DifficultyLevel level = DifficultyLevel.BEGINNER;

			if (difficulty.equals("Medium")) {
				level = DifficultyLevel.MEDIUM;
				boardRequest = new BoardRequest(level, player.getName());
			} else if (difficulty.equals("Ace")) {
				level = DifficultyLevel.ACE;
				boardRequest = new BoardRequest(level, player.getName());
			} else if (difficulty.equals("Custom")) {
				level = DifficultyLevel.CUSTOM;
				boardRequest = new BoardRequest(playRequest.getRows(), playRequest.getColumns(), playRequest.getMines(),
						player.getName());
			} else {
				boardRequest = new BoardRequest(level, player.getName());
			}

			boardRequestService.saveRequest(boardRequest);
		} else {
			boardRequest = boardRequestService.findByPlayer(player.getName());
			existPlayRequest = true;
		}

		if (!existPlayRequest) {
			Cell[][] matrixBoard = minesweeperService.initializeGame(boardRequest, board);

			// TODO Mines generation ETC
		}
		model.put("minesweeperBoard", board);
		model.put("boardRequest", boardRequest);
		model.put("difficulty", difficulty);
		return "newGame";
	}

	@GetMapping(value = "/continueGame")
	public String continueGame(Map<String, Object> model, HttpServletRequest request) {
		Principal player = request.getUserPrincipal();

		MinesweeperBoard board = this.minesweeperService.findByPlayer(player.getName());
		BoardRequest boardRequest = boardRequestService.findByPlayer(player.getName());

		model.put("minesweeperBoard", board);
		model.put("boardRequest", boardRequest);
		return "newGame";
	}

	@GetMapping(value = "/restartGame")
	public String restartGame(Map<String, Object> model, HttpServletRequest request) {
		Principal player = request.getUserPrincipal();

		MinesweeperBoard board = this.minesweeperService.findByPlayer(player.getName());
		BoardRequest boardRequest = boardRequestService.findByPlayer(player.getName());
		this.minesweeperService.deleteMinesweeperBoard(board);
		board = new MinesweeperBoard(player.getName());
		minesweeperService.saveBoard(board);
		Cell[][] matrixBoard = minesweeperService.initializeGame(boardRequest, board);

		model.put("minesweeperBoard", board);
		model.put("boardRequest", boardRequest);
		return "newGame";
	}
}
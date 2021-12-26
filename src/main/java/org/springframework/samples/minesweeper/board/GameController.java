package org.springframework.samples.minesweeper.board;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {
	
	
	@Autowired
	MinesweeperBoardService minesweeperService;
	
	@GetMapping(value = "/selectGame")
	public String selectGame(Map<String, Object> model) {
		return "selectGame";
	}
	
	@GetMapping(value = "/newGame")
	public String newGame(@RequestParam("difficulty") Optional<String> difficulty, Map<String, Object> model) {
		/*MinesweeperBoard board = new MinesweeperBoard();
		minesweeperService.saveBoard(board);
		//BoardRequest boardRequest = new BoardRequest(DifficultyLevel.BEGINNER,request.getUserPrincipal().getName());
		BoardRequest boardRequest = new BoardRequest(DifficultyLevel.ACE,null);
		Cell [][] matrixBoard = minesweeperService.initializeGame(boardRequest, board);
		minesweeperService.saveBoard(board);
		model.put("minesweeperBoard",board);
		model.put("boardRequest",boardRequest);*/
		model.put("minesweeperBoard", minesweeperService.findById(1).get());
		model.put("difficulty", difficulty);
		return "newGame";
	}
	


	

	
	
}
package org.springframework.samples.minesweeper.board;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.minesweeper.audit.Audit;
import org.springframework.samples.minesweeper.audit.AuditService;
import org.springframework.samples.minesweeper.configuration.AdminStats;
import org.springframework.samples.minesweeper.configuration.AdminStatsService;
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
	MinesweeperBoardService minesweeperService;

	@Autowired
	private BoardRequestService boardRequestService;
	@Autowired
	private CellService cellService;
	@Autowired
	private AuditService auditService;
	@Autowired
	private AdminStatsService adminStatsService;
	
	@GetMapping(value = "/configAchievements")
	public String configAchievements(Map<String, Object> model, HttpServletRequest request) {
		
		Integer bronzeMinimumGames = this.adminStatsService.getMinimumGamesByLevel("BRONZE");
		Integer silverMinimumGames = this.adminStatsService.getMinimumGamesByLevel("SILVER");
		Integer goldMinimumGames = this.adminStatsService.getMinimumGamesByLevel("GOLD");
		
		model.put("bronzeMinimumGames", bronzeMinimumGames);
		model.put("silverMinimumGames", silverMinimumGames);
		model.put("goldMinimumGames", goldMinimumGames);
		
		return "admin/configAchievements";
	}
	
	@GetMapping(value = "/updateAchievements")
	public String updateAchievements(Map<String, Object> model, HttpServletRequest request,
			@RequestParam("bronzeGames") int bronzeGames,
			@RequestParam("silverGames") int silverGames,
			@RequestParam("goldGames") int goldGames) {
		
		AdminStats bronzeStats = this.adminStatsService.getStatsByLevel("BRONZE");
		bronzeStats.setGames(bronzeGames);
		
		AdminStats silverStats = this.adminStatsService.getStatsByLevel("SILVER");
		silverStats.setGames(silverGames);
		
		AdminStats goldStats = this.adminStatsService.getStatsByLevel("GOLD");
		goldStats.setGames(goldGames);
		
		this.adminStatsService.saveAdminStats(bronzeStats);
		this.adminStatsService.saveAdminStats(silverStats);
		this.adminStatsService.saveAdminStats(goldStats);
		
		model.put("bronzeMinimumGames", bronzeGames);
		model.put("silverMinimumGames", silverGames);
		model.put("goldMinimumGames", goldGames);
		
		return "admin/configAchievements";
	}
	
	@GetMapping(value = "/gameStats")
	public String gameStats(Map<String, Object> model, HttpServletRequest request) {
		
		List<Audit> games = this.auditService.findAllNotCancelledOrStarted();
		
		// HALL OF FAME (TOP 3 RANKING)
		List<Object[]> hallOfFame = this.auditService.getHallOfFame();
		Object[] top1 = hallOfFame.get(0);
		Object[] top2 = hallOfFame.get(1);
		Object[] top3 = hallOfFame.get(2);
		
		String playerTop1Username = ((String) top1[0]).toUpperCase();
		String playerTop2Username = ((String) top2[0]).toUpperCase();
		String playerTop3Username = ((String) top3[0]).toUpperCase();
		model.put("playerTop1Username", playerTop1Username);
		model.put("playerTop2Username", playerTop2Username);
		model.put("playerTop3Username", playerTop3Username);
		
		long playerTop1WinGames = (long) top1[1];
		long playerTop2WinGames = (long) top2[1];
		long playerTop3WinGames = (long) top3[1];
		model.put("playerTop1WinGames", playerTop1WinGames);
		model.put("playerTop2WinGames", playerTop2WinGames);
		model.put("playerTop3WinGames", playerTop3WinGames);
		
		// GLOBAL STATS
		int numberGlobalGames;
		int averageNumberGlobalGames;
		int averageDurationGlobalGames;
		int totalDurationGlobalGames;
		int maxDurationGlobalGames;
		int minDurationGlobalGames;
		
		// Number of games played (total)
		numberGlobalGames = games.size();
		model.put("numberGlobalGames", numberGlobalGames);
		
		// Average games played by player
		List<Double> listGamesByPlayer = this.auditService.getNumberGamesByPlayer();
		double sumGamesByPlayer = listGamesByPlayer.stream().mapToDouble(Double::doubleValue).sum();
		averageNumberGlobalGames = (int) Math.ceil(sumGamesByPlayer/listGamesByPlayer.size());
		model.put("averageNumberGlobalGames", averageNumberGlobalGames);
		
		// Average duration games 
		List<Double> durationGames = new ArrayList<Double>();
		
		for(Audit g:games) {
			long diffInMillies = Math.abs(g.getEndDate().getTime()-g.getStartDate().getTime());
			double diff = (double) TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
			durationGames.add(Math.ceil(diff));
		}
		
		double sumDurationGames = durationGames.stream().mapToDouble(Double::doubleValue).sum();
		averageDurationGlobalGames = (int) Math.ceil(sumDurationGames/durationGames.size());
		model.put("averageDurationGlobalGames", averageDurationGlobalGames);
		
		// Total duration games played
		totalDurationGlobalGames = (int) sumDurationGames;
		model.put("totalDurationGlobalGames", totalDurationGlobalGames);
		
		// Maximum duration of games played
		maxDurationGlobalGames = Collections.max(durationGames,null).intValue();
		model.put("maxDurationGlobalGames", maxDurationGlobalGames);
		
		// Minimum duration of games played
		minDurationGlobalGames = Collections.min(durationGames,null).intValue();
		if(minDurationGlobalGames<=0)
			minDurationGlobalGames = 1;
		model.put("minDurationGlobalGames", minDurationGlobalGames);
		
		// PLAYER STATS
		
		// Miscellaneous
		
		// Game stats

		// Achievements
		Integer bronzeMinimumGames = this.adminStatsService.getMinimumGamesByLevel("BRONZE");
		Integer silverMinimumGames = this.adminStatsService.getMinimumGamesByLevel("SILVER");
		Integer goldMinimumGames = this.adminStatsService.getMinimumGamesByLevel("GOLD");
		
		model.put("bronzeMinimumGames", bronzeMinimumGames);
		model.put("silverMinimumGames", silverMinimumGames);
		model.put("goldMinimumGames", goldMinimumGames);
		return "players/gameStats";
	}

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
	public String finishGame(@RequestParam(required=false) boolean winner,
			Map<String, Object> model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Principal player = request.getUserPrincipal();

		MinesweeperBoard board = this.minesweeperService.findByPlayer(player.getName());
		BoardRequest boardRequest = boardRequestService.findByPlayer(player.getName());
		boolean foundAnyMine = this.cellService.findAnyMine(board.getId());

		this.minesweeperService.deleteMinesweeperBoard(board);
		boardRequestService.deleteRequest(boardRequest);
		
		// WIN GAME
		if(winner) {
			redirectAttributes.addAttribute("winner", true);
			
			// End audit game (WIN GAME)
			Date date = this.minesweeperService.getFormattedDate();
			Audit gameAudit = this.auditService.findByActiveBoard(board.getId());
			gameAudit.setEndDate(date);
			gameAudit.setPlayer(player.getName());
			gameAudit.setGameStatus("WON");
			gameAudit.setDifficulty(boardRequest.getLevel().name());
			gameAudit.setFinished(true);
			this.auditService.saveAudit(gameAudit);
			log.info(String.format("GAME OVER - Player '%s' has WON the game!",
					player.getName()));
		}else if(!foundAnyMine) {
			// End audit game (CANCELLED GAME)
			Date date = this.minesweeperService.getFormattedDate();
			Audit gameAudit = this.auditService.findByActiveBoard(board.getId());
			gameAudit.setEndDate(date);
			gameAudit.setPlayer(player.getName());
			gameAudit.setGameStatus("CANCELLED");
			gameAudit.setDifficulty(boardRequest.getLevel().name());
			gameAudit.setFinished(true);
			this.auditService.saveAudit(gameAudit);
			log.info(String.format("GAME OVER - Player '%s' has cancelled the game",
					player.getName()));
		}
		
		return "redirect:/welcome";
	}

	@GetMapping(value = "/newGame")
	public String newGame(@RequestParam(value = "difficulty", required = false) String difficulty,
			Map<String, Object> model, BoardRequest playRequest, HttpServletRequest request, @RequestParam(required=false) Integer timer) {
		Principal player = request.getUserPrincipal();
		
		int flagsInMines = 0;
		MinesweeperBoard board = null;
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
		
		// Manage the board of the player
		if (!minesweeperService.existsBoardForPlayer(player.getName())) {
			board = new MinesweeperBoard(player.getName());
			minesweeperService.saveBoard(board);
		} else {
			board = minesweeperService.findByPlayer(player.getName());
			for (Cell c: board.getCells()) {
				if(c.getType().equals("FLAG") || c.getType().equals("MINE-GUESSED")) {
					flagsInMines--;
				}
			}
		}

		// Manage the miscellanious
		boolean foundAnyMine = false;
		if (!existPlayRequest) {
			
			// Initialize board
			Cell[][] matrixBoard = minesweeperService.initializeGame(boardRequest, board);
			
			// Locale mines arround for all cells
			minesweeperService.localeMinesArround(boardRequest, matrixBoard);
			
			// Start audit game (STARTED GAME)
			Date date = this.minesweeperService.getFormattedDate();
			Audit gameAudit = new Audit();
			gameAudit.setStartDate(date);
			gameAudit.setPlayer(player.getName());
			gameAudit.setGameStatus("STARTED");
			gameAudit.setDifficulty(boardRequest.getLevel().name());
			gameAudit.setFinished(false);
			gameAudit.setMinesweeperBoardId(board.getId());
			this.auditService.saveAudit(gameAudit);
		}else {
			foundAnyMine = this.cellService.findAnyMine(board.getId());
			boardRequest.setTimer(timer);
			boardRequestService.saveRequest(boardRequest);
		}
		
		// LOSE GAME
		if(foundAnyMine) {
			model.put("loserMessage", "Sorry, you've lost...");
			
			// End audit game (LOSE GAME)
			Date date = this.minesweeperService.getFormattedDate();
			Audit gameAudit = this.auditService.findByActiveBoard(board.getId());
			gameAudit.setEndDate(date);
			gameAudit.setPlayer(player.getName());
			gameAudit.setGameStatus("LOST");
			gameAudit.setDifficulty(boardRequest.getLevel().name());
			gameAudit.setFinished(true);
			this.auditService.saveAudit(gameAudit);
			log.info(String.format("GAME OVER - Player '%s' has lost the game",
					player.getName()));
		}

		model.put("flagsInMines", flagsInMines);
		model.put("minesweeperBoard", board);
		model.put("boardRequest", boardRequest);
		model.put("difficulty", difficulty);
		model.put("timer", timer);
		return "newGame";
	}

	@GetMapping(value = "/continueGame")
	public String continueGame(Map<String, Object> model, HttpServletRequest request) {
		Principal player = request.getUserPrincipal();
		int flagsInMines = 0;

		MinesweeperBoard board = this.minesweeperService.findByPlayer(player.getName());
		for (Cell c: board.getCells()) {
			if(c.getType().equals("FLAG") || c.getType().equals("MINE-GUESSED")) {
				flagsInMines--;
			}
		}
		
		BoardRequest boardRequest = boardRequestService.findByPlayer(player.getName());
		flagsInMines = flagsInMines + boardRequest.getMines();
		
		// Retieve the previous time and always add the page change delay (2 seconds)
		int timer = boardRequest.getTimer()+2;
		
		model.put("flagsInMines", flagsInMines);
		model.put("minesweeperBoard", board);
		model.put("boardRequest", boardRequest);
		model.put("timer", timer);
		return "newGame";
	}

	@GetMapping(value = "/restartGame")
	public String restartGame(Map<String, Object> model, HttpServletRequest request) {
		Principal player = request.getUserPrincipal();
		
		MinesweeperBoard board = this.minesweeperService.findByPlayer(player.getName());
		BoardRequest boardRequest = boardRequestService.findByPlayer(player.getName());
		boolean foundAnyMine = this.cellService.findAnyMine(board.getId());
		
		if(!foundAnyMine) {
			// End audit game (CANCELLED GAME)
			Date date = this.minesweeperService.getFormattedDate();
			Audit gameAudit = this.auditService.findByActiveBoard(board.getId());
			gameAudit.setEndDate(date);
			gameAudit.setPlayer(player.getName());
			gameAudit.setGameStatus("CANCELLED");
			gameAudit.setDifficulty(boardRequest.getLevel().name());
			gameAudit.setFinished(true);
			this.auditService.saveAudit(gameAudit);
		}
				
		this.minesweeperService.deleteMinesweeperBoard(board);
		board = new MinesweeperBoard(player.getName());
		minesweeperService.saveBoard(board);
		
		// Initialize board
		Cell[][] matrixBoard = minesweeperService.initializeGame(boardRequest, board);
		
		// Locale mines arround for all cells
		minesweeperService.localeMinesArround(boardRequest, matrixBoard);
		
		// Start audit game (STARTED GAME)
		Date newDate = this.minesweeperService.getFormattedDate();
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
		model.put("timer", 0);
		return "newGame";
	}
}
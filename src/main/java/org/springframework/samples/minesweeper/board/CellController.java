package org.springframework.samples.minesweeper.board;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.minesweeper.model.BoardRequest;
import org.springframework.samples.minesweeper.model.BoardRequestService;
import org.springframework.samples.minesweeper.player.PlayerStats;
import org.springframework.samples.minesweeper.player.PlayerStatsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CellController {

	@Autowired
	private CellService cellService;

	@Autowired
	private MinesweeperBoardService minesweeperService;
	@Autowired
	private BoardRequestService boardRequestService;
	@Autowired
	private PlayerStatsService playerStatsService;

	@GetMapping(value = "/cells/update")
	public String initUpdateCellForm(@RequestParam("xPosition") int xPosition, @RequestParam("yPosition") int yPosition,
			@RequestParam("move") String move, Model model, HttpServletRequest request, final RedirectAttributes redirectAttributes,
			@RequestParam(required=false) Integer flagsInMines,
			@RequestParam(required=false) Integer timer) {
		Principal player = request.getUserPrincipal();
		BoardRequest boardRequest = boardRequestService.findByPlayer(player.getName());
		
		Boolean flagWarning = null;
		
		// Retrieve current player stats
		PlayerStats playerStats = this.playerStatsService.getPlayerStats(player.getName());

		Cell cell = this.cellService.findCellByPosition(xPosition - 1, yPosition - 1);
		if (!cell.getType().equals("PRESSED")) {
			if (move.equals("uncover")) {
				// Update number of cells clicked (PLAYER STATS)
				playerStats.setNumberCellsClicked(playerStats.getNumberCellsClicked()+1);
				
				if (!cell.getType().equals("FLAG")) {
					// When press a mine cell
					if (cell.isMine()) {

						// Uncover the rest of mines
						MinesweeperBoard board = this.minesweeperService.findByPlayer(player.getName());
						List<Cell> cells = this.minesweeperService.getAllCells(board.getId());
						for (Cell c : cells) {
							// Show mine
							if(c.isMine() && !c.getType().contentEquals("FLAG")) {
								c.setType("MINE");
							// Show mine guessed
							}else if(c.isMine() && c.getType().contentEquals("FLAG")) {
								c.setType("MINE-GUESSED");
								
								// Update number of guessed mines (PLAYER STATS)
								playerStats.setNumberGuessedMines(playerStats.getNumberGuessedMines()+1);
							}
						}

						// Uncover current selected mine
						cell.setType("MINE-PRESSED");
						
						// Update number of activated mines (PLAYER STATS)
						playerStats.setNumberActivatedMines(playerStats.getNumberActivatedMines()+1);
						
					// When press a no-mine cell
					} else {

						// Clear cells and set numbers mines around on cells are near from clear cells
						minesweeperService.clearEmptySpots(xPosition - 1, yPosition - 1, boardRequest.getRows() - 1,
								boardRequest.getColumns() - 1);
						this.cellService.checkMinesAround(cell);
					}
				} //An exception shows up when you try to uncover a flagged cell
				else if(cell.getType().equals("FLAG")) {
					flagWarning = true;
				}
			} else if (move.equals("flag")) {
				// Update number of cells clicked (PLAYER STATS)
				playerStats.setNumberCellsClicked(playerStats.getNumberCellsClicked()+1);
				
				if (cell.getType().equals("FLAG")) {
					// Unplace a flag
					cell.setType("UNPRESSED");
					
					// Update number of flags placed. Substract one (PLAYER STATS)
					playerStats.setNumberTotalFlags(playerStats.getNumberTotalFlags()-1);
				}  // Condition to not flag a uncovered cell with a number on it
				else if (cell.getType().equals("UNPRESSED") || cell.getType().equals("PRESSED")) {
					if(flagsInMines>0) {
						// Place a flag
						cell.setType("FLAG");
						
						// Update number of flags placed. Add one (PLAYER STATS)
						playerStats.setNumberTotalFlags(playerStats.getNumberTotalFlags()+1);
					}
				}
			} 
		}
		this.cellService.saveCell(cell);

		boolean alreadyWon = minesweeperService.alreadyWon(boardRequest);
		
		// Saving current PLAYER STATS
		this.playerStatsService.savePlayerStats(playerStats);

		// WIN GAME
		if (alreadyWon) {
			redirectAttributes.addAttribute("winner", true);
			redirectAttributes.addAttribute("timer", timer);
			redirectAttributes.addAttribute("flagsInMines", flagsInMines);
			return "redirect:/finishGame";

			// CONTINUE GAME
		} else {
			model.addAttribute(cell);
			model.addAttribute("minesweeperBoard", cell.getMinesweeperBoard());
			model.addAttribute("flagsInMines", flagsInMines);
			redirectAttributes.addAttribute("flagWarning", flagWarning);
			redirectAttributes.addAttribute("timer", timer);
			return "redirect:/newGame";
		}
	}
}
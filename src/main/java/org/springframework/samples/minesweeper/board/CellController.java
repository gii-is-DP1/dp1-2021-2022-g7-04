package org.springframework.samples.minesweeper.board;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.minesweeper.model.BoardRequest;
import org.springframework.samples.minesweeper.model.BoardRequestService;
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

	@GetMapping(value = "/cells/update")
	public String initUpdateCellForm(@RequestParam("xPosition") int xPosition, @RequestParam("yPosition") int yPosition,
			@RequestParam("move") String move, Model model, HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {
		Principal player = request.getUserPrincipal();
		BoardRequest boardRequest = boardRequestService.findByPlayer(player.getName());

		Cell cell = this.cellService.findCellByPosition(xPosition - 1, yPosition - 1);
		if (!cell.getType().equals("PRESSED")) {
			if (move.equals("uncover")) {
				if (!cell.getType().equals("FLAG")) {
					// When press a mine cell
					if (cell.isMine()) {

						// Uncover the rest of mines
						MinesweeperBoard board = this.minesweeperService.findByPlayer(player.getName());
						List<Cell> cells = this.minesweeperService.getAllCells(board.getId());
						for (Cell c : cells) {
							if (c.isMine()) {
								c.setType("MINE");
							}
						}

						// Uncover current selected mine
						cell.setType("MINE-PRESSED");

						// When press a no-mine cell
					} else {

						// Clear cells and set numbers mines around on cells are near from clear cells
						minesweeperService.clearEmptySpots(xPosition - 1, yPosition - 1, boardRequest.getRows() - 1,
								boardRequest.getColumns() - 1);
						this.cellService.checkMinesAround(cell);
					}
				}
			} else if (move.equals("flag")) {
				if (cell.getType().equals("FLAG")) {
					cell.setType("UNPRESSED");
				}  // Condition to not flag a uncovered cell with a number on it
				else if (cell.getType().equals("UNPRESSED") || cell.getType().equals("PRESSED")) {
					cell.setType("FLAG");
				}
			}
		}
		this.cellService.saveCell(cell);

		boolean alreadyWon = minesweeperService.alreadyWon(boardRequest);

		// WIN GAME
		if (alreadyWon) {
			redirectAttributes.addAttribute("winner", true);
			return "redirect:/finishGame";

			// CONTINUE GAME
		} else {
			model.addAttribute(cell);
			model.addAttribute("minesweeperBoard", cell.getMinesweeperBoard());
			return "redirect:/newGame";
		}
	}
}
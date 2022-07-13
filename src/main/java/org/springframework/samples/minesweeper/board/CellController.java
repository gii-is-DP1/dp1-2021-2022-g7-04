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
			@RequestParam("move") String move, Model model, HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		Principal player = request.getUserPrincipal();
		BoardRequest boardRequest = boardRequestService.findByPlayer(player.getName());
		
		Cell cell = this.cellService.findCellByPosition(xPosition - 1, yPosition - 1);
		if (!cell.getType().equals("PRESSED")) {
			if (move.equals("uncover")) {
				if (!cell.getType().equals("FLAG")) {
					if (cell.isMine()) {
						cell.setType("MINE");
						
						// Uncover the rest of mines
						MinesweeperBoard board = this.minesweeperService.findByPlayer(player.getName());
						List<Cell> cells = this.minesweeperService.getAllCells(board.getId());
						for(Cell c:cells) {
							if(c.isMine()) {
								c.setType("MINE");
							}
						}
					}else {
						minesweeperService.clearEmptySpots(xPosition - 1, yPosition - 1, boardRequest.getRows() - 1, boardRequest.getColumns() - 1);
						switch(cell.getMinesAround()) {
						case 1:
							cell.setType("ONE");
							break;
						case 2:
							cell.setType("TWO");
							break;
						case 3:
							cell.setType("THREE");
							break;
						case 4:
							cell.setType("FOUR");
							break;
						case 5:
							cell.setType("FIVE");
							break;
						case 6:
							cell.setType("SIX");
							break;
						case 7:
							cell.setType("SEVEN");
							break;
						case 8:
							cell.setType("HEIGHT");
							break;
						default:
							cell.setType("PRESSED");
						}
					}
				}				
			} else if (move.equals("flag")) {
				if (cell.getType().equals("FLAG")) {
					cell.setType("UNPRESSED");
				} //Condición en el else para que no se pueda poner bandera a una casilla con número	
				else if(cell.getType().equals("UNPRESSED") || cell.getType().equals("PRESSED")){ 
					cell.setType("FLAG");
				}
			} 
		}
		
		this.cellService.saveCell(cell);
		
		boolean alreadyWon = minesweeperService.alreadyWon(boardRequest);
		
		// WIN GAME
		if(alreadyWon) {
			redirectAttributes.addAttribute("winner", true);
			return "redirect:/finishGame";
		
		// CONTINUE GAME
		}else {
			model.addAttribute(cell);
			model.addAttribute("minesweeperBoard", cell.getMinesweeperBoard());
			return "redirect:/newGame";
		}
		
	}
}
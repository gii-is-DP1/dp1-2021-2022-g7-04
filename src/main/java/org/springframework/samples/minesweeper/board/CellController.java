package org.springframework.samples.minesweeper.board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CellController {	
	
	@Autowired
	private CellService cellService;
	
	@GetMapping(value = "/cells/update")
	public String initUpdateCellForm(@RequestParam("xPosition") int xPosition, @RequestParam("yPosition") int yPosition,
			@RequestParam("move") String move , Model model) {
		Cell cell = this.cellService.findCellByPosition(xPosition-1, yPosition-1);
		if(!cell.getType().equals("PRESSED")) {
			if(move.equals("uncover")) {
				if(!cell.getType().equals("FLAG")) {
					cell.setType("PRESSED");
				}
			} else if(move.equals("flag")) {
				if(cell.getType().equals("FLAG")) {
					cell.setType("UNPRESSED");
				}else {
					cell.setType("FLAG");
				}
			} else {
				if(cell.type.equals("UNPRESSED")) {
					cell.setType("FLAG");
				}
			}
		}
		this.cellService.saveCell(cell);
		model.addAttribute(cell);
		model.addAttribute("minesweeperBoard", cell.getMinesweeperBoard());
		return "redirect:/newGame";
	}
}
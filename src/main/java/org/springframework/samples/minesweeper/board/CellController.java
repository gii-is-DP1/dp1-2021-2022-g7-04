package org.springframework.samples.minesweeper.board;
import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.minesweeper.user.User;
import org.springframework.samples.minesweeper.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CellController {
	
	
	private static final String VIEWS_CELL_UPDATE_FORM = "/welcome";
	
	
	
	
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
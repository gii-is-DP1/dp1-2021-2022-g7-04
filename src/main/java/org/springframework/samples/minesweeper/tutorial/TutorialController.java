package org.springframework.samples.minesweeper.tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TutorialController {
	
	@Autowired
	TutorialService tutorialService;
	
	@GetMapping(value = "/tutorial")
	public String tutorial(Model model) {
		model.addAttribute("goal", tutorialService.findTutorial().getGoal());
		model.addAttribute("players", tutorialService.findTutorial().getPlayers());
		model.addAttribute("howToPlay", tutorialService.findTutorial().getHowToPlay());
		
		String levels = tutorialService.findTutorial().getLevels();
		String concepts= tutorialService.findTutorial().getConcepts();
		
		String[] formattedLevels = levels.split(";");
		String[] formattedConcepts = concepts.split(";");
		
		model.addAttribute("formattedLevels", formattedLevels);
		model.addAttribute("formattedConcepts", formattedConcepts);
		
		return "tutorial/tutorialDetails";
	}
}
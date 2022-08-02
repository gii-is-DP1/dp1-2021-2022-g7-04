package org.springframework.samples.minesweeper.tutorial;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TutorialController {
	@GetMapping(value = "/tutorial")
	public String tutorial() {
		return "tutorial/tutorialDetails";
	}
}
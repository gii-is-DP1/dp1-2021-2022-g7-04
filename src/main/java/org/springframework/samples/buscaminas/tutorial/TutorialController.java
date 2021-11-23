package org.springframework.samples.buscaminas.tutorial;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TutorialController {
	@GetMapping(value = "/tutorial")
	public String tutorial() {
		return "tutorial/tutorialDetails";
	}
}

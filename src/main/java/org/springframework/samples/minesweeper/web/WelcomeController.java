package org.springframework.samples.minesweeper.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.minesweeper.board.MinesweeperBoardService;
import org.springframework.samples.minesweeper.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class WelcomeController {
	

	@Autowired
	MinesweeperBoardService minesweeperService;
	
	@GetMapping({"/","/welcome"})
	public String welcome(Map<String, Object> model, HttpServletResponse response) {
		response.addHeader("Refresh","100"); 
		model.put("now", new Date());
		model.put("minesweeperBoard",minesweeperService.findById(1).get());
		List<Person> persons = new ArrayList<Person>();

		String[] developers = new String[] {
				"José Manuel.Lobato Troncoso",
				"Daniel Jesús.Martínez Suárez",
				"Bogdan Marian.Stefan",
				"Luis.Cerrato Sánchez",
				"Regina.Escalera Martín",
				"Ernesto.Gutiérrez Contreras"
		};

		for(String dev:developers) {
			String[] parts = dev.split("\\.");
			Person person = new Person();
			person.setFirstName(parts[0]);
			person.setLastName(parts[1]);
			persons.add(person);
		}

		model.put("persons", persons);
		model.put("title", "Design & Testing I - Minesweeper");
		model.put("group", "G7-04");

		return "welcome";
	}
}
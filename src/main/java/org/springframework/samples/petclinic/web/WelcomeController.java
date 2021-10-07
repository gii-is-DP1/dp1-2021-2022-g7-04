package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.samples.petclinic.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	    
		  List<Person> persons = new ArrayList<Person>();
		  
		  String[] developers = new String[] {
				  "José Manuel.Lobato Troncoso",
				  "Daniel Jesús.Martínez Suárez",
				  "Bogdan.Marian Stefan",
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
		  model.put("title", "Design & Testing I - Pet Clinic");
		  model.put("group", "G7-04");
		  
		  return "welcome";
	  }
}

package org.springframework.samples.minesweeper.user;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	private static final String VIEWS_SERVICE_CREATE_FORM = "users/createUserForm";
	private static final String VIEWS_SERVICE_UPDATE_FORM = "users/updateUserForm";

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/users/new")
	public String initCreationForm(Map<String, Object> model) {
		User user = new User();
		model.put("user", user);
		
		return VIEWS_SERVICE_CREATE_FORM;
	}

	@PostMapping(value = "/users/new")
	public String processCreationForm(@Valid User user, BindingResult result) {

		if (result.hasErrors()) {
			return VIEWS_SERVICE_CREATE_FORM;
		} else {
			// creating user, and authority
			this.userService.saveUser(user);

			return "redirect:/users/" + user.getUsername();
		}
	}

	@GetMapping(value = "/users/{username}/edit")
	public String initUpdateUserForm(@PathVariable("username") String username, Model model) {
		Optional<User> user = this.userService.findUser(username);
		model.addAttribute(user);
		
		return VIEWS_SERVICE_UPDATE_FORM;
	}

	@PostMapping(value = "/users/{username}/edit")
	public String processUpdateUserForm(@Valid User user, BindingResult result,
			@PathVariable("username") String username) {
		if (result.hasErrors()) {
			return VIEWS_SERVICE_UPDATE_FORM;
		} else {
			user.setUsername(username);
			this.userService.saveUser(user);
			
			return "redirect:/users/{username}";
		}
	}

	@GetMapping("/users/{username}")
	public ModelAndView showUser(@PathVariable("username") String username) {
		// this details can only be seen by the administrator or the logged user
		ModelAndView mav = new ModelAndView("users/userDetails");
		mav.addObject(this.userService.findUser(username));
		
		return mav;
	}
}
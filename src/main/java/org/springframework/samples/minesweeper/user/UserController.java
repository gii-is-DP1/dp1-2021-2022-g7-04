/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.minesweeper.user;

import java.util.Map;

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
		User user = this.userService.findByUsername(username);
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
		// comprobar que solo pueda ver estos detalles un admin o el user que se haya
		// logueado
		ModelAndView mav = new ModelAndView("users/userDetails");
		mav.addObject(this.userService.findByUsername(username));
		return mav;
	}


	@GetMapping(value = "/{username}/delete")
	public String logicDeleteuser(@PathVariable("username") String username) {
		userService.logicDeleteUser(username);
		return "users/userLogicDelete";
	}
}
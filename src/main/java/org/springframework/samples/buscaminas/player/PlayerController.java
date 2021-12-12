package org.springframework.samples.buscaminas.player;
import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlayerController {
	
	private static final String VIEWS_PLAYER_CREATE_FORM = "players/createPlayerForm";
	private static final String VIEWS_PLAYER_UPDATE_FORM = "players/updatePlayerForm";
	
	@Autowired
	private PlayerService playerService;
	
	@GetMapping(value = "/players/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("player", new Player());
		return "players/findPlayers";
	}
	

	@GetMapping(value = "/players/list")
	public String processFindForm(Player player, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /owners to return all records
		if (player.getFirstName() == null) {
			player.setFirstName(""); // empty string signifies broadest possible search	
		}

		// find owners by username
		Collection<Player> results = this.playerService.findPlayers(player.getUsername());
		if (results.isEmpty()) {
			// no owners found
			result.rejectValue("username", "notFound", "not found");
			return "players/findPlayers";
		}
		else {
			model.put("selections", results);
			return "players/playersList";
		}
	}
	
	
	@GetMapping("/players/{username}")
	public ModelAndView showPlayer(@PathVariable("username") String username) {
		ModelAndView mav = new ModelAndView("players/playerDetails");
		mav.addObject(this.playerService.findPlayerByUsername(username));
		return mav;
	}
	
	@GetMapping(value = "/players/{username}/edit")
	public String initUpdatePlayerForm(@PathVariable("username") String username, Model model) {
		Player player = this.playerService.findPlayerByUsername(username);
		model.addAttribute(player);
		return VIEWS_PLAYER_UPDATE_FORM;
	}
	
	@PostMapping(value = "/players/{username}/edit")
	public String processUpdatePlayerForm(@Valid Player player, BindingResult result,
			@PathVariable("username") String username) {
		if (result.hasErrors()) {
			return VIEWS_PLAYER_UPDATE_FORM;
		}
		else {
			player.setUsername(username);
			this.playerService.saveplayer(player);
			return "redirect:/players/{username}";
		}
	}
	/*
	@PostMapping(value = "/players/{username}/edit")
	public String processUpdatePlayerForm(@Valid Player player, BindingResult result,
			@PathVariable("username") String username) {
		if (result.hasErrors()) {
			return VIEWS_PLAYER_CREATE_OR_UPDATE_FORM;
		} else {
			
			this.playerService.saveplayer(player);
			return "redirect:/players/{username}";
		}
		
		*/
	
	
}
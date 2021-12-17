package org.springframework.samples.minesweeper.player;
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
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlayerController {
	
	private static final String VIEWS_PLAYER_CREATE_FORM = "players/createPlayerForm";
	private static final String VIEWS_PLAYER_UPDATE_FORM = "players/updatePlayerForm";
	
	private static final String VIEWS_PLAYER_CREATE_OR_UPDATE_FORM = "players/createOrUpdatePlayerForm";
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private UserService userService;
	
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
		Collection<Player> results = this.playerService.findPlayers(player.getFirstName());
		if (results.isEmpty()) {
			// no owners found
			result.rejectValue("firstName", "notFound", "not found");
			return "players/findPlayers";
		}
		else {
			model.put("selections", results);
			return "players/playersList";
		}
	}
	
	
	@GetMapping("/players/{playerId}")
	public ModelAndView showPlayer(@PathVariable("playerId") int playerId) {
		ModelAndView mav = new ModelAndView("players/playerDetails");
		mav.addObject(this.playerService.findPlayerById(playerId));
		return mav;
	}
	
	@GetMapping(value = "/players/new")
	public String initCreationForm(Map<String, Object> model) {
		Player player = new Player();
		model.put("player", player);
		return VIEWS_PLAYER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/players/new")
	public String processCreationForm(@Valid Player player, BindingResult result) {
		
		if (result.hasErrors()) {
			return VIEWS_PLAYER_CREATE_OR_UPDATE_FORM;
		}
		else {
			
			this.playerService.savePlayer(player);
			
			return "redirect:/players/" + player.getId();
		}
	}
	
	@GetMapping(value = "/players/{playerId}/edit")
	public String initUpdatePlayerForm(@PathVariable("playerId") int playerId, Model model) {
		Player player = this.playerService.findPlayerById(playerId);
		model.addAttribute(player);
		return VIEWS_PLAYER_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/players/{playerId}/edit")
	public String processUpdatePlayerForm(@Valid Player player, BindingResult result,
			@PathVariable("playerId") int playerId) {
		if (result.hasErrors()) {
			return VIEWS_PLAYER_CREATE_OR_UPDATE_FORM;
		}
		else {
			player.setId(playerId);
			this.playerService.savePlayer(player);
			return "redirect:/players/{playerId}";
		}
	}
	
	@PostMapping(value="/{playerId}/delete")
	public String deletePlayer(@PathVariable("playerId") int playerId) {
		playerService.deletePlayer(playerId);
		return "redirect:/";
	}
	

	
	
}
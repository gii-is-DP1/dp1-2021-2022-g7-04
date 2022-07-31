package org.springframework.samples.minesweeper.player;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.samples.minesweeper.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class PlayerController {

	private static final String VIEWS_PLAYER_CREATE_OR_UPDATE_FORM = "players/createOrUpdatePlayerForm";

	@Autowired
	private PlayerService playerService;

	@GetMapping(value = "/players/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("player", new Player());
		return "players/findPlayers";
	}

	@GetMapping(value = "/players/list")
	public String processFindForm(Player player, BindingResult result, Map<String, Object> model,@PageableDefault(page = 0, size = 5)@SortDefault.SortDefaults({
		@SortDefault(sort = "id", direction = Sort.Direction.ASC),
		@SortDefault(sort = "firstName", direction = Sort.Direction.DESC)})Pageable pageable) {
		
			// allow parameterless GET request for /players to return all records
			player = this.playerService.checkPlayerSearched(player);
			
			Integer nresults = this.playerService.countFoundedPlayers(player.getFirstName());
			Integer page = 0;
			// find players by first name
			List<Player> results = this.playerService.findPlayers(player.getFirstName(),page,pageable);
			model.put("pageNumber", pageable.getPageNumber());
			model.put("hasPrevious", pageable.hasPrevious());
			model.put("firstName", player.getFirstName());
			Double totalPages = Math.ceil(nresults/(pageable.getPageSize())); // 
			model.put("totalPages", totalPages);
			if (results.isEmpty()) {
				// no players found
				result.rejectValue("firstName", "notFound", "not found");
				return "players/findPlayers";
			} else {
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
		} else {

			this.playerService.savePlayer(player);
			
			log.info(String.format("MANAGE GAME - '%s' has registered as a new player. Welcome!", player.getUser().getUsername()));

			return "redirect:/login";
		}
	}

	@GetMapping(value = "/players/{playerId}/edit")
	public String initUpdatePlayerForm(@PathVariable("playerId") int playerId, Model model) {
		Player player = this.playerService.findPlayerById(playerId).get();
		model.addAttribute(player);
		return VIEWS_PLAYER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/players/{playerId}/edit")
	public String processUpdatePlayerForm(@Valid Player player, BindingResult result,
			@PathVariable("playerId") int playerId) {
		if (result.hasErrors()) {
			return VIEWS_PLAYER_CREATE_OR_UPDATE_FORM;
		} else {
			player.setId(playerId);
			this.playerService.savePlayer(player);
	
			return "redirect:/players/"+player.getUser().getUsername();
		}
	}
	
	@GetMapping(value = "/{username}/delete")
	public String deletePlayer(@PathVariable("username") String username) {
		playerService.deletePlayer(username);
		return "players/playerDelete";
	}
}
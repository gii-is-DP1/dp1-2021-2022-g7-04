package org.springframework.samples.minesweeper.player;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


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
			if (player.getFirstName() == null) {
				player.setFirstName(""); // empty string signifies broadest possible search
			}
			Integer page=0;
			// find players by username
			List<Player> results = this.playerService.findPlayers(player.getFirstName(),page,pageable);
			model.put("pageNumber", pageable.getPageNumber());
			model.put("pageSize", pageable.getPageSize());
			model.put("hasPrevious", pageable.hasPrevious());
			Integer playersPerPage = pageable.getPageSize();
			Double totalPages = Math.ceil(playerService.findAll().size()/(playersPerPage+1));
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
		} else {

			this.playerService.savePlayer(player);

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
			return "redirect:/players/{playerId}";
		}
	}
}
package org.springframework.samples.minesweeper.player;
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
public class JugadorController {
	private static final String VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM = "jugadores/createOrUpdateJugadorForm";
	@Autowired
	private JugadorService jugadorService;
	
	@GetMapping(value = "/jugadores/buscar")
	public String initFindForm(Map<String, Object> model) {
		model.put("jugador", new Jugador());
		return "jugadores/findJugadores";
	}
	

	@GetMapping(value = "/jugadores/list")
	public String processFindForm(Jugador jugador, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /owners to return all records
		if (jugador.getNombre() == null) {
			jugador.setNombre(""); // empty string signifies broadest possible search
		}

		// find owners by last name
		Collection<Jugador> results = this.jugadorService.findJugadorByName(jugador.getNombre());
		if (results.isEmpty()) {
			// no owners found
			result.rejectValue("nombre", "notFound", "not found");
			return "jugadores/findJugadores";
		}
		else if (results.size() == 1) {
			// 1 owner found
			jugador = results.iterator().next();
			return "redirect:/jugadores/" + jugador.getId();
		}
		else {
			// multiple owners found
			model.put("selections", results);
			return "jugadores/jugadoresList";
		}
	}
	
	
	@GetMapping("/jugadores/{jugadorId}")
	public ModelAndView showJugador(@PathVariable("jugadorId") int jugadorId) {
		ModelAndView mav = new ModelAndView("jugadores/jugadorDetails");
		mav.addObject(this.jugadorService.findJugadorById(jugadorId));
		return mav;
	}
	
	@GetMapping(value = "/jugadores/{jugadorId}/edit")
	public String initUpdateJugadorForm(@PathVariable("jugadorId") int jugadorId, Model model) {
		Jugador jugador = this.jugadorService.findJugadorById(jugadorId);
		model.addAttribute(jugador);
		return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/jugadores/{jugadorId}/edit")
	public String processUpdateJugadorForm(@Valid Jugador jugador, BindingResult result,
			@PathVariable("jugadorId") int jugadorId) {
		if (result.hasErrors()) {
			return VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
		} else {
			jugador.setId(jugadorId);
			this.jugadorService.saveJugador(jugador);
			return "redirect:/jugadores/{jugadorId}";
		}
	}
}
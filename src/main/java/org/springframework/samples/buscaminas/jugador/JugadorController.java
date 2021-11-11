package org.springframework.samples.buscaminas.jugador;
import java.util.Collection;

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
	
	@GetMapping("/jugadores")
	public String jugadoresList(ModelMap modelMap) {
		String vista = "jugadores/jugadoresList";
		Iterable<Jugador> jugadores = jugadorService.findAll();
		System.out.println(jugadores);
		modelMap.addAttribute("jugadores",jugadores);
		return vista;
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